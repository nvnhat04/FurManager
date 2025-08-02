package com.example.furmanager.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.furmanager.R
import com.example.furmanager.ui.components.CartItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun ProfileScreen(navController: NavController, viewModel: AuthViewModel) {
    var imageRes by remember { mutableIntStateOf(R.drawable.sofa) }
    val currentUser = FirebaseAuth.getInstance().currentUser
    val uid = currentUser ?.uid ?: return
    val nameState = remember { mutableStateOf("Loading...") }
    val query = remember { mutableStateOf("") }
    // Firestore gọi bên trong SideEffect để tránh gọi nhiều lần
    LaunchedEffect(uid) {
        val db = FirebaseFirestore.getInstance()
        db.collection("users").document(uid)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    nameState.value = document.getString("name") ?: "No name"
                }
            }
            .addOnFailureListener {
                nameState.value = "Error"
            }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Profile Screen",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(250.dp))
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Column(

        ) {
            Text(
                text = "User Information",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            OutlinedTextField(
                value = nameState.value,
                onValueChange = {},
                label = { Text("Name") },
                enabled = false,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Email
            OutlinedTextField(
                value = currentUser.email.toString(),
                onValueChange = {},
                label = { Text("Email") },
                enabled = false,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                viewModel.signOut {
                    navController.navigate("login") {
                        popUpTo(0) { inclusive = true }
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



//@Preview(showBackground = true)
//@Composable
//fun ShowProfileScreenPreview() {
//    val dummyNavController = rememberNavController()
//
//    // Tạo giả AuthViewModel với signOut là lambda trống
//    val mockViewModel = object : AuthViewModel() {
////        override fun signOut(onResult: () -> Unit) {
////            // Không làm gì cả trong preview
////        }
//    }
//
//    ProfileScreen(
//        navController = dummyNavController,
//        viewModel = mockViewModel
//    )
//}
