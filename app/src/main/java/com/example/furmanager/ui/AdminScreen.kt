package com.example.furmanager.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.furmanager.ui.components.Header
import com.example.furmanager.ui.components.Product
import com.example.furmanager.ui.components.ProductGrid
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun AdminScreen(navController: NavController){
    val products = remember { mutableStateListOf<Product>() }
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        FirebaseFirestore.getInstance()
            .collection("products")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    Log.e("Firestore", "Lỗi khi lắng nghe sản phẩm: ${error.message}")
                    return@addSnapshotListener
                }

                snapshot?.let {
                    val fetchedProducts = snapshot.documents.mapNotNull { doc ->
                        doc.toObject(Product::class.java)?.copy(id = doc.id)
                    }

                    Log.d("Firestore", "Số lượng sản phẩm: ${fetchedProducts.size}")
                    products.clear()
                    products.addAll(fetchedProducts)

                    if (fetchedProducts.isEmpty()) {
                        Toast.makeText(context, "Không có sản phẩm nào", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }

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
    Column(modifier = Modifier.padding(8.dp)){

        Header("All product")
        ProductGrid(products = products, navController = navController, role= role.toString())
    }
}