package com.example.furmanager.ui

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

//@Preview(showBackground = true)
@Composable
fun RegisterScreen(viewModel: AuthViewModel, navController: NavController) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current
    var isLoading by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Create Account",
            style = MaterialTheme.typography.headlineSmall
        )
        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )

        error?.let { Text(it, color = Color.Red) }


        Button(onClick = {
            isLoading = true
            viewModel.register(name, email, password,
                onSuccess = {
                    isLoading = false
                    Toast.makeText(context, "Đăng ký thành công!", Toast.LENGTH_SHORT).show()
                    navController.navigate("home")
                },
                onError = {
                    isLoading = false
                    error = it
                }
            )
        }) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(20.dp), color = Color.White)
            } else {
                Text("Register")
            }
        }

        TextButton(onClick = { navController.navigate("login") }) {
            Text("Already have an account?")
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun PreviewRegister() {
//    RegisterScreen()
//}
