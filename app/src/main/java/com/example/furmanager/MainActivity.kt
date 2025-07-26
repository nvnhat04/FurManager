package com.example.furmanager

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.furmanager.navigation.NaviGraph
import com.example.furmanager.ui.components.NaviBar
import androidx.compose.runtime.getValue

import com.example.furmanager.ui.theme.FurmanagerTheme
import com.google.firebase.auth.FirebaseAuth


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            // Danh sách route được phép hiện bottom bar
            val bottomBarRoutes = listOf("home", "profile", "cart")

            Scaffold(
                bottomBar = {
                    if (currentRoute in bottomBarRoutes) {
                        NaviBar(navController)
                    }
                }
            ) {
                Box(modifier = Modifier.padding(it)) {
                    NaviGraph(navController)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FurmanagerTheme {
        Greeting("Android")
    }
}