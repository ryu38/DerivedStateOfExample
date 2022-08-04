package com.example.deriveddemo.ui.samples

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Composable
fun LazyColumnExample() {
    val itemHeight = 60
    val itemCount = 20
    val visibleItemCount = 5
    val itemHeightPx = with(LocalDensity.current) { itemHeight.dp.roundToPx() }

    SnapHelpedLazyColumn(
        itemHeightPx = itemHeightPx,
        onIndexChange = {
            Log.v("MY_TEST", "$it")
        },
        modifier = Modifier
            .fillMaxWidth()
            .height((visibleItemCount * itemHeight).dp)
    ) {
        items(itemCount) {
            Text(
                "$it",
                fontSize = 32.sp,
                modifier = Modifier
                    .height(itemHeight.dp)
                    .wrapContentHeight()
            )
        }
    }
}

@Composable
private fun SnapHelpedLazyColumn(
    itemHeightPx: Int,
    modifier: Modifier = Modifier,
    initialItemIndex: Int = 0,
    onIndexChange: (Int) -> Unit = {},
    content: LazyListScope.() -> Unit,
) {
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = initialItemIndex
    )
    val currentOnIndexChange by rememberUpdatedState(onIndexChange)

    val targetIndex = remember(itemHeightPx) {
        derivedStateOf {
            when {
                listState.firstVisibleItemScrollOffset <= itemHeightPx / 2 ->
                    listState.firstVisibleItemIndex
                else ->
                    listState.firstVisibleItemIndex + 1
            }
        }
    }

    val connection = remember {
        object : NestedScrollConnection {
            override suspend fun onPostFling(
                consumed: Velocity,
                available: Velocity,
            ): Velocity {
                scope.launch {
                    listState.animateScrollToItem(targetIndex.value)
                }
                return Velocity.Zero
            }
        }
    }

    LaunchedEffect(Unit) {
        snapshotFlow { targetIndex.value }
            .collect {
                currentOnIndexChange(it)
            }
    }

    SideEffect {
        Log.v("MY_TEST", "Composed!")
    }

    Box(
        modifier = Modifier.nestedScroll(connection)
    ) {
        LazyColumn(
            state = listState,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {
            content()
        }
    }
}

@Preview
@Composable
private fun SnapHelpedLazyColumnPreview() {
    val itemHeight = 30
    val itemCount = 10
    val visibleItemCount = 3
    val itemHeightPx = with(LocalDensity.current) { itemHeight.dp.roundToPx() }

    Surface {
        SnapHelpedLazyColumn(
            itemHeightPx = itemHeightPx,
            modifier = Modifier
                .width(60.dp)
                .height((visibleItemCount * itemHeight).dp)
        ) {
            items(itemCount) {
                Text(
                    "$it",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .height(itemHeight.dp)
                        .wrapContentHeight()
                )
            }
        }
    }
}