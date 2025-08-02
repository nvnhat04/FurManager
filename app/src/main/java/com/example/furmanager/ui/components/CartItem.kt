package com.example.furmanager.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import com.example.furmanager.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CheckboxDefaults.colors
import androidx.compose.material3.FloatingActionButtonDefaults.elevation
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.TextButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun CartItem(
    title: String,
    imageRes: Int,
    price: String,
    onDetailClick: () -> Unit,
    onDelete: () -> Unit,
    role: String
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = onDetailClick
    ) {
        Column(

        ) {
            Row(
                modifier = Modifier
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = title,
                    modifier = Modifier
                        .size(64.dp)
                        .padding(end = 16.dp)
                )

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black,

                        )
                    Text(
                        text = price,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }

            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                if (role == "admin") {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        TextButton(onClick = onDelete) {
                            Text("Delete")
                        }
                        TextButton(onClick = {}) {
                            Text("Edit")
                        }
                    }
                }


            }
        }
    }
}

@Preview
@Composable
fun showCart(){
    CartItem(
        title = "Sofa Xịn",
        imageRes = R.drawable.sofa, // ảnh nằm trong res/drawable
        price = "$299",
        onDetailClick = {
            // Điều hướng hoặc hiển thị chi tiết
        }
        ,
        onDelete = {},
        role = "admin"
    )

}