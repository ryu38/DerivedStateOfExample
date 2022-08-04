package com.example.deriveddemo.ui.samples

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun SnapshotFlowSample() {
    Column {
        SnapshotFlow(
            onStateChange = {
                Log.v("MY_TEST", "x: $it")
            }
        )
        LaunchedEffectKey(
            onStateChange = {
                Log.v("MY_TEST", "x: $it")
            }
        )
    }
}

@Composable
private fun SnapshotFlow(
    onStateChange: (Int) -> Unit
) {
    val x = remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        snapshotFlow { x.value }
            .distinctUntilChanged()
            .collect {
                onStateChange(it)
            }
    }

    SideEffect {
        Log.v("MY_TEST", "SnapshotFlow re-composed")
    }

    Button(onClick = { x.value++ }) {
        Text("SnapshotFlow")
    }
}

@Composable
private fun LaunchedEffectKey(
    onStateChange: (Int) -> Unit
) {
    val x = remember { mutableStateOf(0) }

    LaunchedEffect(x.value) {
        onStateChange(x.value)
    }

    SideEffect {
        Log.v("MY_TEST", "LaunchedEffectKey re-composed")
    }

    Button(onClick = { x.value++ }) {
        Text("LaunchedEffectKey")
    }
}

@Preview(showBackground = true)
@Composable
private fun SnapshotFlowSamplePreview() {
    SnapshotFlowSample()
}