package com.example.furmanager.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.furmanager.ui.AddProductScreen
import com.example.furmanager.ui.AdminScreen
import com.example.furmanager.ui.AuthViewModel
import com.example.furmanager.ui.CartScreen
import com.example.furmanager.ui.CheckOutScreen
import com.example.furmanager.ui.DetailScreen
import com.example.furmanager.ui.GetLayout
import com.example.furmanager.ui.HomeScreen
import com.example.furmanager.ui.LoginScreen
import com.example.furmanager.ui.ProfileScreen
import com.example.furmanager.ui.RegisterScreen

@Composable
fun NaviGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "main"
    ){
        composable("main") {
            GetLayout (
                navController = navController
            )
        }

        composable("login") {
            LoginScreen(
                viewModel = viewModel(),
                navController = navController
            )
        }
        composable("register") {
            RegisterScreen(
                viewModel = viewModel(),
                navController = navController
            )
        }
//        composable(
//            route = "home/{username}",
//            arguments = listOf(navArgument("username") { defaultValue = "" })
//        ) { backStackEntry ->
//            val username = backStackEntry.arguments?.getString("username") ?: ""
//            HomeScreen(
//                username = username,
//                onSignOut = {navController.navigate("login")}
//            )
//        }
        composable("home") {
            HomeScreen(
                viewModel = viewModel(),
                navController = navController
            )
        }
        composable("cart") {
            CartScreen(
                navController = navController
            )
        }
        composable("checkout") {
            CheckOutScreen(
                navController = navController
            )
        }
        composable("profile") {
            ProfileScreen(
                navController = navController,
                viewModel = viewModel()
            )
        }
        composable("add-product") {
            AddProductScreen(

            )
        }
        composable("admin") {
            AdminScreen(
                navController = navController
            )
        }
        composable("detail") {
            DetailScreen(
                navController = navController
            )
        }
    }
}