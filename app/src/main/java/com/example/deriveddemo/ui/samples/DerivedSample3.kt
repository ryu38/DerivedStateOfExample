package com.example.deriveddemo.ui.samples

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.floor

@Composable
fun DerivedSample3() {
    Column {
        Text(
            "derivedStateOf",
            fontWeight = FontWeight.Bold,
        )
        DerivedCounter()
        Divider(Modifier.padding(vertical = 8.dp))
        Text(
            "remember",
            fontWeight = FontWeight.Bold,
        )
        RememberCounter()
    }
}

@Composable
private fun DerivedCounter() {
    val x = remember { mutableStateOf(0) }

    val y = remember {
        derivedStateOf { floor(x.value / 10f).toInt() }
    }

    SideEffect {
        Log.v("MY_TEST", "derivedStateOf")
    }

    Column {
        Text("x / 10 = ${y.value}")
        Button(onClick = { x.value++ }) {
            Text("x+")
        }
    }
}

@Composable
private fun RememberCounter() {
    val x = remember { mutableStateOf(0) }

    val y = remember(x.value) { floor(x.value / 10f).toInt() }

    SideEffect {
        Log.v("MY_TEST", "remember")
    }

    Column {
        Text("x / 10 = $y")
        Button(onClick = { x.value++ }) {
            Text("x+")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DerivedSamplePreview() {
    DerivedSample1()
}