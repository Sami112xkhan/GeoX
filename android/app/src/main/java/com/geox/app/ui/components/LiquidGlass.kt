package com.geox.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.geox.app.theme.GlassWhite
import com.geox.app.theme.GlassWhiteSubtle

@Composable
fun LiquidGlass(
    modifier: Modifier = Modifier,
    subtle: Boolean = false,
    cornerRadius: androidx.compose.ui.unit.Dp = 24.dp,
    content: @Composable () -> Unit = {}
) {
    Box(
        modifier = modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = if (subtle) {
                        listOf(
                            GlassWhiteSubtle,
                            Color(0x75FFFFFF)
                        )
                    } else {
                        listOf(
                            GlassWhite,
                            Color(0xB3FFFFFF)
                        )
                    }
                ),
                shape = RoundedCornerShape(cornerRadius)
            )
            .border(
                width = 1.dp,
                color = Color.White.copy(alpha = if (subtle) 0.35f else 0.5f),
                shape = RoundedCornerShape(cornerRadius)
            )
    ) {
        content()
    }
}

