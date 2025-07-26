package com.example.furmanager.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.furmanager.R
import com.google.firebase.auth.FirebaseAuth

@Composable
fun HomeScreen(viewModel: AuthViewModel, navController: NavController) {
    val user = FirebaseAuth.getInstance().currentUser
    val sampleProducts = listOf(
        Product("Nike", "Air Max 90", "$129.99", R.drawable.sofa),
        Product("Adidas", "Ultraboost", "$149.99", R.drawable.sofa),
        Product("Puma", "RS-X", "$109.99",R.drawable.sofa)
    )


    Column(
        modifier = Modifier.padding(8.dp),

    ){
        Text("Xin chào, ${user?.email}")
        ProductSlider(products = sampleProducts)
        Button(onClick = {
            viewModel.signOut {
                navController.navigate("login") {
                    // Xóa backstack để không quay lại màn Home
                    popUpTo("home") { inclusive = true }
                }
            }
        }) {
            Text("Sign Out")
        }

    }
}