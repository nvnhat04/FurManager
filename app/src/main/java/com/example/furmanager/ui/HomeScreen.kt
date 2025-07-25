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
import com.google.firebase.auth.FirebaseAuth

@Composable
fun HomeScreen(viewModel: AuthViewModel, navController: NavController) {
    val user = FirebaseAuth.getInstance().currentUser

    Column(
        modifier = Modifier.fillMaxWidth().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ){
        Text("Xin chào, ${user?.email}")
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