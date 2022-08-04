package com.example.deriveddemo.ui.samples

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun ComposeTutorial() {
    val a = remember { mutableStateOf(0) }

    SideEffect {
        Log.v("MY_TEST", "re-composed")
    }

    Column {
        Text("${a.value}")
        Button(onClick = { a.value++ }) {
            Text("a++")
        }
    }
}