package com.example.furmanager.ui
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.furmanager.ui.components.ProductItem
import com.google.accompanist.pager.*
import com.example.furmanager.R
import androidx.compose.foundation.lazy.items
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.furmanager.ui.components.Product


@Composable
fun ProductSlider(products: List<Product>, navController: NavController) {
    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
//        HorizontalPager(
//            count = products.size,
//            state = pagerState,
//            contentPadding = PaddingValues(horizontal = 32.dp),
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(250.dp)
//        ) { page ->
//            ProductItem(
//                brand = products[page].brand,
//                title= products[page].title,
//                price = products[page].price,
//                imageUrl = products[page].imageUrl
//            )
//        }
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(products) { product ->
                ProductItem(
                    title= product.title,
                    price = product.price,
                    imageUrl = product.imageUrl,
                    onDetailClick = {
                        navController.navigate("cart")
                    }
                 )

            }
        }
        // Optional: Dot Indicator
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp)
        )
    }
}
@Preview
@Composable
fun showSlide(){
    val sampleProducts = listOf(
        Product("Air Max 90", "$129.99", R.drawable.sofa),
        Product("Ultraboost", "$149.99", R.drawable.sofa),
        Product("RS-X", "$109.99",R.drawable.sofa)
    )
    val navController = rememberNavController()
    ProductSlider(products = sampleProducts, navController = navController)
}
