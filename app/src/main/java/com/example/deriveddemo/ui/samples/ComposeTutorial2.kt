package com.example.deriveddemo.ui.samples

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ComposeTutorial2() {
    val parentState = remember { mutableStateOf(0) }

    SideEffect {
        Log.v("MY_TEST", "Parent re-composed")
    }

    Column {
        Text("parent ${parentState.value}")
        Button(onClick = { parentState.value++ }) {
            Text("parent++")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Child(
            parentState = parentState.value
        )
    }
}

@Composable
fun Child(
    parentState: Int
) {
    val childState = remember { mutableStateOf(0) }

    SideEffect {
        Log.v("MY_TEST", "Child re-composed")
    }

    Column {
        Text("child + parent = ${parentState + childState.value}")
        Button(onClick = { childState.value++ }) {
            Text("child++")
        }
    }
}