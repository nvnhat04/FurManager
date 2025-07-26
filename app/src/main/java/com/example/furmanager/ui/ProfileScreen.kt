package com.example.furmanager.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun ProfileScreen(navController: NavController, onLogout: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Profile Screen")
        Button(onClick = { onLogout() }) {
            Text("Logout")
        }
    }
}
