package com.example.furmanager.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.furmanager.R
import com.example.furmanager.ui.components.CartObject
import com.example.furmanager.ui.components.Product
import com.example.furmanager.ui.components.ProductItem
import java.nio.file.WatchEvent

@Composable
fun CartScreen(navController: NavController) {
    val sampleProducts = listOf(
        Product("Air Max 90", "$129.99", R.drawable.sofa),
        Product("Ultraboost", "$149.99", R.drawable.sofa),
        Product("RS-X", "$109.99", R.drawable.sofa),
        Product("Air Max 90", "$129.99", R.drawable.table),
        Product("Ultraboost", "$149.99", R.drawable.sofa),
        Product("RS-X", "$109.99", R.drawable.table),
        Product("RS-X", "$109.99", R.drawable.sofa)
    )

    val countList = remember { mutableStateListOf(*Array(sampleProducts.size) { 0 }) }

    // Tính tổng tiền theo countList
    val totalPrice = sampleProducts.indices.sumOf { i ->
        val price = sampleProducts[i].price.replace(Regex("[^0-9.]"), "").toDoubleOrNull() ?: 0.0
        price * countList[i]
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "My Cart",
                style = MaterialTheme.typography.headlineSmall
            )
        }
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(horizontal = 16.dp),
        ) {
            itemsIndexed(sampleProducts) { index, product ->
                CartObject(
                    title = product.title,
                    price = product.price,
                    imageUrl = product.imageUrl,
                    count = countList[index],
                    onCountChange = { newCount ->
                        countList[index] = newCount
                    },
                    onDetailClick = {
                        // navController.navigate("product/${product.id}")
                    }
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Subtotal: $${"%.2f".format(totalPrice)}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black
            )
            Text(
                text = "Ship: $${"%.2f".format(totalPrice)}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black
            )
            Text(
                text = "Taxes: $${"%.2f".format(totalPrice)}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black
            )
            Text(
                text = "Total: $${"%.2f".format(totalPrice)}",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Black
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {

            }) {
                Text(
                    text = "Buy",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {

            }) {
                Text(
                    text = "Cancel",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            }
        }
    }
}

@Preview
@Composable
fun showCartObj(){
    val navController = rememberNavController()

    CartScreen(navController)
}