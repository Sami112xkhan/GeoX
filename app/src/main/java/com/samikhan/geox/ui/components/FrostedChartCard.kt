package com.samikhan.geox.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FrostedChartCard(
    modifier: Modifier = Modifier,
    title: String,
    content: @Composable () -> Unit
) {
    FrostedCard(modifier = modifier, title = title) {
        content()
    }
}


