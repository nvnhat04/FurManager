package com.example.furmanager.ui.components

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.furmanager.R
import com.example.furmanager.ui.ProductSlider
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun ProductGrid(products: List<Product>, navController: NavController, role: String) {
    var context = LocalContext.current
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(products.size) { index ->
            val product = products[index]
            CartItem(
                title = product.title,
                imageRes = product.imageUrl,
                price = product.price,
                onDetailClick = {
                    navController.navigate("detail")
                },
                onDelete = {
                    deleteProductById(product.id,
                        onSuccess = {
                            Toast.makeText(
                               context,
                                "Xóa sản phẩm thành công",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        onError = { msg ->
                            Toast.makeText(
                                context,
                                msg,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    )
                },
                role = role
            )
        }
    }
}
fun deleteProductById(productId: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
    FirebaseFirestore.getInstance()
        .collection("products")
        .document(productId)
        .delete()
        .addOnSuccessListener { onSuccess() }
        .addOnFailureListener { e -> onError(e.message ?: "Xóa thất bại") }
}

@Preview
@Composable
fun showSlide(){
    val sampleProducts = listOf(
        Product("1","Air Max 90", "$129.99", R.drawable.sofa),
        Product("2","Ultraboost", "$149.99", R.drawable.sofa),
        Product("3","RS-X", "$109.99", R.drawable.sofa, ),
        Product("4","Air Max 90", "$129.99", R.drawable.sofa),
        Product("5","Ultraboost", "$149.99", R.drawable.sofa),
        Product("6","RS-X", "$109.99", R.drawable.sofa)
    )
    val navController = rememberNavController()
    ProductGrid(products = sampleProducts, navController = navController, role="admin")
}

