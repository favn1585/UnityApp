package com.example.unityapp.feature.actions

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.unityapp.ui.theme.UnityAppTheme


@Composable
fun ActionsScreen() {


    ActionsScreenContent()
}

@Composable
fun ActionsScreenContent() {
    Button(onClick = { /*TODO*/ }) {
        Text(text = "Do Action")
    }
}


@Preview(showBackground = true)
@Composable
fun ActionsScreenPreview() {
    UnityAppTheme {
        ActionsScreen()
    }
}