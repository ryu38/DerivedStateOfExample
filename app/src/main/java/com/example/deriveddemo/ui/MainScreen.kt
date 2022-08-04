package com.example.deriveddemo.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.deriveddemo.ui.samples.*

@Composable
fun MainScreen() {
    val pageList = remember { Page.values() }
    val pageIndex = remember { mutableStateOf(
        pageList.indexOf(Page.SAMPLE1)
    ) }

    Scaffold(
        topBar = { TopAppBar(
            title = {
                Text("DerivedStateOf勉強会")
            },
            actions = {
                IconButton(
                    onClick = { pageIndex.value-- },
                    enabled = pageIndex.value > 0
                ) {
                    Icon(Icons.Default.KeyboardArrowLeft, contentDescription = null)
                }
                Text(
                    pageIndex.value.toString(),
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                IconButton(
                    onClick = { pageIndex.value++ },
                    enabled = pageIndex.value < pageList.size - 1
                ) {
                    Icon(Icons.Default.KeyboardArrowRight, contentDescription = null)
                }
            }
        ) }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (pageList[pageIndex.value]) {
                Page.TUTORIAL -> ComposeTutorial()
                Page.TUTORIAL2 -> ComposeTutorial2()
                Page.SAMPLE1 -> DerivedSample1()
                Page.SAMPLE2 -> DerivedSample2()
                Page.SAMPLE3 -> DerivedSample3()
                Page.TIPS -> DerivedTips()
                Page.SNAPSHOT_FLOW -> SnapshotFlowSample()
                Page.EXAMPLE -> LazyColumnExample()
            }
        }
    }
}

enum class Page {
    TUTORIAL,
    TUTORIAL2,
    SAMPLE1,
    SAMPLE2,
    SAMPLE3,
    TIPS,
    SNAPSHOT_FLOW,
    EXAMPLE
}