package com.example.furmanager.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.furmanager.ui.GetLayout
import com.example.furmanager.ui.HomeScreen
import com.example.furmanager.ui.LoginScreen
import com.example.furmanager.ui.RegisterScreen

@Composable
fun NaviGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "main"
    ){
        composable("main") {
            GetLayout (
                title = "Main",
                onBack = {
                    navController.navigate("login")
                }
            )
        }

        composable("login") {
            LoginScreen(
                navController = navController,
                onRegister = {navController.navigate("register")}
            )
        }
        composable("register") {
            RegisterScreen(
                onRegister = {navController.navigate("main")},
                onAlreadyHaveAccount = { navController.navigate("login") }
            )
        }
        composable(
            route = "home/{username}",
            arguments = listOf(navArgument("username") { defaultValue = "" })
        ) { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: ""
            HomeScreen(
                username = username,
                onSignOut = {navController.navigate("login")}
            )
        }
    }
}