package com.example.furmanager.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.furmanager.R
import com.example.furmanager.ui.ProductSlider

@Composable
fun ProductGrid(products: List<Product>, navController: NavController) {
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
                    navController.navigate("cart")
                }
            )
        }
    }
}
@Preview
@Composable
fun showSlide(){
    val sampleProducts = listOf(
        Product("Air Max 90", "$129.99", R.drawable.sofa),
        Product("Ultraboost", "$149.99", R.drawable.sofa),
        Product("RS-X", "$109.99",R.drawable.sofa),
        Product("Air Max 90", "$129.99", R.drawable.sofa),
        Product("Ultraboost", "$149.99", R.drawable.sofa),
        Product("RS-X", "$109.99",R.drawable.sofa)
    )
    val navController = rememberNavController()
    ProductGrid(products = sampleProducts, navController = navController)
}

