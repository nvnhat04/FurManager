package com.example.furmanager.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        placeholder = { Text("Search products...") },
        modifier = Modifier
            .fillMaxWidth().padding(2.dp).clip(RoundedCornerShape(20.dp)) ,
        singleLine = true,
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
        }
    )
}
