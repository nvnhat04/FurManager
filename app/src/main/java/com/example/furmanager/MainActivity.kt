package com.example.furmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.furmanager.navigation.NaviGraph

import com.example.furmanager.ui.theme.FurmanagerTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            FurmanagerTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                   GetLayout(
//                        title = "Android",
//                        innerPadding = PaddingValues(10.dp)
//                    )
//
//                }
//            }

            val navController = rememberNavController()
            NaviGraph(navController)

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