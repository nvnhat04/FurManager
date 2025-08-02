package com.example.furmanager.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.furmanager.R
import com.example.furmanager.ui.components.AutoSlidingBanner
import com.example.furmanager.ui.components.BannerData
import com.example.furmanager.ui.components.CartObject
import com.example.furmanager.ui.components.Header
import com.example.furmanager.ui.components.Product
import com.example.furmanager.ui.components.ProductGrid
import com.example.furmanager.ui.components.ProductItem
import com.example.furmanager.ui.components.SearchBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun HomeScreen(viewModel: AuthViewModel, navController: NavController) {
    val user = FirebaseAuth.getInstance().currentUser
    val uid = user?.uid ?: return

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

                    Log.d("Firestore", "Số lượng sản phẩm: ${fetchedProducts}")
                    products.clear()
                    products.addAll(fetchedProducts)

                    if (fetchedProducts.isEmpty()) {
                        Toast.makeText(context, "Không có sản phẩm nào", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }


    val sampleProducts = listOf(
        Product("1","Air Max 90", "$129.99", R.drawable.sofa),
        Product("2","Ultraboost", "$149.99", R.drawable.sofa),
        Product("3","RS-X", "$109.99", R.drawable.sofa, ),
        Product("4","Air Max 90", "$129.99", R.drawable.sofa),
        Product("5","Ultraboost", "$149.99", R.drawable.sofa),
        Product("6","RS-X", "$109.99", R.drawable.sofa)
    )
    val banners = listOf(
        BannerData("Banner 1", R.drawable.sofa),
        BannerData("Banner 2", R.drawable.table),
        BannerData("Banner 3", R.drawable.sofa)
    )

    Column(modifier = Modifier.padding(8.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Welcome to FurManager, ${nameState.value}",
                fontWeight = FontWeight.SemiBold,
                color = Color(200, 155, 255),
                fontSize = 16.sp,
                fontFamily = FontFamily.SansSerif
            )
        }
        // searcg bar
        SearchBar(
            query = query.value,
            onQueryChange = { query.value = it }
        )


        AutoSlidingBanner(banners = banners)
        Header("Top monthly")
        ProductSlider(products = products, navController= navController)
        Header("Recently")
        ProductGrid(products = products ?: sampleProducts, navController = navController, role = role.toString())


    }
}

