package com.example.furmanager.ui

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.furmanager.ui.components.Product
import com.google.firebase.firestore.FirebaseFirestore
import java.util.UUID

@Composable
fun AddProductScreen() {
    val context = LocalContext.current
//    val firestore = FirebaseFirestore.getInstance()

    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Add Product", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
        OutlinedTextField(value = price, onValueChange = { price = it }, label = { Text("Price") })

        Spacer(modifier = Modifier.height(12.dp))



        Button(onClick = {
            addProductToFirebase(context,name, price)
            name = ""
            price = ""
        }) {
            Text("Submit")
        }
    }
}
fun addProductToFirebase(context: Context, name: String, price: String) {
    if (name.isBlank() || price.isBlank()) {
        Toast.makeText(context, "Vui lòng nhập đầy đủ tên và giá", Toast.LENGTH_SHORT).show()
        return
    }

    val pricePattern = Regex("^\\$?\\d+(\\.\\d{1,2})?\$") // kiểm tra giá hợp lệ
    if (!pricePattern.matches(price)) {
        Toast.makeText(context, "Giá không hợp lệ (ví dụ: 100 hoặc 99.99)", Toast.LENGTH_SHORT).show()
        return
    }

    val db = FirebaseFirestore.getInstance()
    val product = Product(
        title = name.trim(),
        price = price.trim(),

    )

    db.collection("products")
        .add(product)
        .addOnSuccessListener {
            Toast.makeText(context, "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show()
        }
        .addOnFailureListener {
            Toast.makeText(context, "Lỗi khi thêm sản phẩm: ${it.message}", Toast.LENGTH_LONG).show()
        }
}



@Preview
@Composable
fun showAddProduct(){
    AddProductScreen()
}