package com.example.furmanager.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun GetLayout(title: String = "Hello", innerPadding: PaddingValues = PaddingValues(0.dp), onBack: () -> Unit) {
    var count by rememberSaveable { mutableIntStateOf(0) }

    Column (
        modifier = Modifier.fillMaxWidth().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        GetTextTitle("MENU")
        Text(
            text = "You have clicked $count times"
        )
        Button(onClick = {
            count++
        }) {
            Text (text = "click me!")
        }
        Button(onClick = onBack) {
            Text(text = "Return")
        }

    }
}
@Composable
fun GetTextTitle(title: String = "Hello", innerPadding: PaddingValues = PaddingValues(0.dp)) {
    Text(
        text = title,
        modifier = Modifier.padding(innerPadding).fillMaxWidth(),
        color = Color.Red,
        textAlign = TextAlign.Center

    )
}