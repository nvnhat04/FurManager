package com.example.furmanager.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ProfileScreen(navController: NavController, viewModel: AuthViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Profile Screen")
        Button(
            onClick = {
                viewModel.signOut {
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.Black
            ),
            elevation = ButtonDefaults.buttonElevation(0.dp)

        ) {
            Text("Sign Out")
        }
    }
}
