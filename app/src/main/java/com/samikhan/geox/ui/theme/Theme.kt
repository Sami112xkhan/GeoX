package com.samikhan.geox.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize

private val LightColors = lightColorScheme(
    primary = Blue500,
    onPrimary = White,
    secondary = Blue700,
    onSecondary = White,
    background = White,
    onBackground = Black,
    surface = White,
    onSurface = Black,
)

private val DarkColors = darkColorScheme(
    primary = Blue300,
    onPrimary = Black,
    secondary = Blue500,
    onSecondary = Black,
)

@Composable
fun GeoXTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors
    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes
    ) {
        Box(
            modifier = Modifier.background(
                brush = Brush.verticalGradient(
                    0f to Color(0xFF0B1020),
                    1f to Color(0xFF000000)
                )
            ).fillMaxSize()
        ) {
            content()
        }
    }
}


