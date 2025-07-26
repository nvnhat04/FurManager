package com.example.furmanager.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Header(text: String){
    Text(
        text = text,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.SansSerif,
        color = Color(0xFF333333),
        modifier = Modifier.fillMaxWidth().padding(10.dp)

    )

}
