package com.example.deriveddemo.ui.samples

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DerivedSample2() {
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
    val a = remember { mutableStateOf(0) }
    val b = remember { mutableStateOf(0) }

    val c = remember {
        derivedStateOf { addWithLog(a.value, b.value, "derivedStateOf") }
    }

    val d = remember { mutableStateOf(0) }

    Column {
        Text("a + b = ${c.value}")
        Row {
            Button(onClick = { a.value++ }) {
                Text("a+")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { b.value++ }) {
                Text("b+")
            }
        }
        Text("d: ${d.value}")
        Button(onClick = { d.value++ }) {
            Text("d+")
        }
    }
}

@Composable
private fun RememberCounter() {
    val a = remember { mutableStateOf(0) }
    val b = remember { mutableStateOf(0) }

    val c = remember(a.value, b.value) {
        addWithLog(a.value, b.value, "remember")
    }

    val d = remember { mutableStateOf(0) }

    Column {
        Text("a + b = $c")
        Row {
            Button(onClick = { a.value++ }) {
                Text("a+")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { b.value++ }) {
                Text("b+")
            }
        }
        Text("d: ${d.value}")
        Button(onClick = { d.value++ }) {
            Text("d+")
        }
    }
}

private fun addWithLog(a: Int, b: Int, message: String): Int {
    Log.v("MY_TEST", message)
    return a + b
}

@Preview(showBackground = true)
@Composable
private fun DerivedSamplePreview() {
    DerivedSample1()
}