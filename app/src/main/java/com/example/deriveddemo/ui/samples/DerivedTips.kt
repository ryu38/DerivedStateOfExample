package com.example.deriveddemo.ui.samples

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlin.math.floor

@Composable
fun DerivedTips() {
    val a = remember { mutableStateOf(0) }
    val b = remember { mutableStateOf(0) }
    Column {
        Text(
            "parameter",
            fontWeight = FontWeight.Bold,
        )
        TipA(a.value)
        Button(onClick = { a.value++ }) {
            Text("a+")
        }
        Divider(Modifier.padding(vertical = 8.dp))
        Text(
            "rememberUpdateState",
            fontWeight = FontWeight.Bold,
        )
        TipB(b)
        Button(onClick = { b.value++ }) {
            Text("b+")
        }
    }
}

@Composable
fun TipA(x: Int) {
    val y = remember {
        derivedStateOf { floor(x / 10f).toInt() }
    }

    SideEffect {
        Log.v("MY_TEST", "A")
    }

    Text("${y.value}")
}

@Composable
fun TipB(x: State<Int>) {
    val y = remember {
        derivedStateOf { floor(x.value / 10f).toInt() }
    }

    SideEffect {
        Log.v("MY_TEST", "B")
    }

    Text("${y.value}")
}