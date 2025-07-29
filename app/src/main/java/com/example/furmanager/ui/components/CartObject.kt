package com.example.furmanager.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.furmanager.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton



@Composable
fun CartObject(
    title: String,
    price: String,
    imageUrl: Int,
    count: Int,
    onCountChange: (Int) -> Unit,
    onDetailClick: () -> Unit
) {
    val priceValue = price.replace(Regex("[^0-9.]"), "").toDoubleOrNull() ?: 0.00

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = onDetailClick
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = imageUrl),
                contentDescription = title,
                modifier = Modifier
                    .size(64.dp)
                    .padding(end = 16.dp)
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(title, style = MaterialTheme.typography.titleMedium)
                Text(price, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
//                Text(
//                    text = "Total: $${"%.2f".format(count * priceInt)}",
//                    style = MaterialTheme.typography.titleMedium,
//                    color = Color.Black
//                )
            }
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = {
                    if (count > 0) onCountChange(count - 1)
                }) {
                    Icon(Icons.Default.Remove, contentDescription = "Giảm")
                }
                Text("$count")
                IconButton(onClick = {
                    onCountChange(count + 1)
                }) {
                    Icon(Icons.Default.Add, contentDescription = "Tăng")
                }
            }
            Text(
                text = "Total: $${"%.2f".format(count.toDouble() * priceValue )}",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )
        }
        }
    }
}


@Preview
@Composable
fun showCartObj(){
    CartObject(
        title = "Sofa Xịn",

        price = "$299",
        imageUrl = R.drawable.sofa, // ảnh nằm trong res/drawable
        count = 1,
        onCountChange = {},
        onDetailClick = {
            // Điều hướng hoặc hiển thị chi tiết
        }
    )
}