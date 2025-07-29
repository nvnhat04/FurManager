package com.example.furmanager.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.furmanager.R
import com.example.furmanager.ui.components.CartObject
import com.example.furmanager.ui.components.Product
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun CheckOutScreen(navController: NavController) {
    val currentUser = FirebaseAuth.getInstance().currentUser
    val uid = currentUser?.uid ?: return
    val nameState = remember { mutableStateOf("Loading...") }

    LaunchedEffect(uid) {
        val db = FirebaseFirestore.getInstance()
        db.collection("users").document(uid)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    nameState.value = document.getString("name") ?: "No name"
                }
            }
            .addOnFailureListener {
                nameState.value = "Error"
            }
    }

    val products = listOf(
        Product("Watermelon", "$10.99", R.drawable.sofa),
        Product("Pear", "$8.99", R.drawable.sofa),
        Product("Pear", "$8.99", R.drawable.table),
        Product("Pear", "$8.99", R.drawable.sofa),

        )

    val subtotal = products.sumOf {
        it.price.replace(Regex("[^0-9.]"), "").toDoubleOrNull() ?: 0.0
    }
    val shipping = 0.0
    val tax = 2.0
    val total = subtotal + tax

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Header
        Text(
            text = "Checkout",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        )

        Divider()

        // Info Sections (Shipping, Delivery, etc.)
        CheckoutInfoRow("SHIPPING", "Add shipping address")
        CheckoutInfoRow("DELIVERY", "Free\nStandard | 3-4 days")
        CheckoutInfoRow("PAYMENT", "Visa *1234")
        CheckoutInfoRow("PROMOS", "Apply promo code")

        Divider()

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(horizontal = 16.dp),
        ) {
            itemsIndexed(products) { index, product ->
                CheckoutProductItem(product = product)
            }

        }


        Divider(modifier = Modifier.padding(vertical = 8.dp))

        // Summary
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            SummaryRow("Subtotal (${products.size})", subtotal)
            SummaryRow("Shipping total", shipping)
            SummaryRow("Taxes", tax)
            SummaryRow("Total", total, bold = true)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Place Order Button
        Button(
            onClick = { /* Handle order */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text(
                text = "Place order",
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun CheckoutInfoRow(label: String, value: String) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable { }
        .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Text(label, style = MaterialTheme.typography.labelMedium, color = Color.Gray)
        Text(value, style = MaterialTheme.typography.bodyLarge)
    }
    Divider()
}

@Composable
fun CheckoutProductItem(product: Product) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = product.imageUrl),
            contentDescription = product.title,
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text("Brand", style = MaterialTheme.typography.labelMedium, color = Color.Gray)
            Text("Product name", style = MaterialTheme.typography.bodyMedium)
            Text("Description", style = MaterialTheme.typography.bodySmall)
            Text("Quantity: 01", style = MaterialTheme.typography.bodySmall)
        }
        Text(
            text = product.price,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.align(Alignment.Top)
        )
    }
}

@Composable
fun SummaryRow(label: String, amount: Double, bold: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, style = if (bold) MaterialTheme.typography.bodyLarge else MaterialTheme.typography.bodyMedium)
        Text(
            text = "$${"%.2f".format(amount)}",
            style = if (bold) MaterialTheme.typography.bodyLarge else MaterialTheme.typography.bodyMedium
        )
    }
}

//// Data class (you may already have this)
//data class Product(
//    val title: String,
//    val price: String,
//    val imageUrl: Int
//)
