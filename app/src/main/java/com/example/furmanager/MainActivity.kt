package com.example.furmanager

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ManageAccounts
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Scaffold

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.furmanager.navigation.NaviGraph
import com.example.furmanager.ui.components.NaviBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.furmanager.ui.components.BottomNavItem

import com.example.furmanager.ui.theme.FurmanagerTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            // Role state
            var role by remember { mutableStateOf<String?>(null) }

            // Lấy role từ Firebase
            LaunchedEffect(Unit) {
                val uid = FirebaseAuth.getInstance().currentUser?.uid
                uid?.let {
                    FirebaseFirestore.getInstance()
                        .collection("users")
                        .document(it)
                        .get()
                        .addOnSuccessListener { doc ->
                            role = doc.getString("role") ?: "user"
                        }
                }
            }
            // Danh sách route được phép hiện bottom bar
            val bottomBarRoutes = listOf("home", "profile", "cart", "checkout")
            val bottomAdminBarRoutes = listOf( "profile", "add-product", "admin",)
            val items = listOf(
                BottomNavItem("Home", Icons.Default.Home, "home"),
                BottomNavItem("Cart", Icons.Default.ShoppingCart, "cart"),
                BottomNavItem("Profile", Icons.Default.Person, "profile"),
                BottomNavItem("CheckOut", Icons.Default.Checklist, "checkout"),


            )
            val itemsAdmin = listOf(
                BottomNavItem("Admin", Icons.Default.ManageAccounts, "admin"),
                BottomNavItem("Profile", Icons.Default.Person, "profile"),
                BottomNavItem("Add", Icons.Default.Add, "add-product")
            )

            Scaffold(
                bottomBar = {
                    if (role != null) {
                        if (role == "admin" && currentRoute in bottomAdminBarRoutes) {
                            NaviBar(navController, itemsAdmin)
                        } else if (currentRoute in bottomBarRoutes) {
                            NaviBar(navController, items)
                        }
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