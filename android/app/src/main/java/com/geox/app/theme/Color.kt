package com.geox.app.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance

// Primary colors
val LimeGreen = Color(0xFFC4FF0D)
val LimeGreenDark = Color(0xFF9FCC0A)

// Disaster colors
val EarthquakeRed = Color(0xFFEF4444)
val WildfireOrange = Color(0xFFF97316)
val VolcanoPurple = Color(0xFFA855F7)
val FloodBlue = Color(0xFF3B82F6)
val StormCyan = Color(0xFF06B6D4)

// Background colors
val BackgroundLight = Color(0xFFFAFAFA)
val BackgroundDark = Color(0xFF050505)
val SurfaceDark = Color(0xFF121212)
val White = Color(0xFFFFFFFF)
val Black = Color(0xFF000000)

// Text color tokens
private val TextPrimaryLight = Color(0xFF0F0F0F)
private val TextPrimaryDark = Color(0xFFF6F6F6)
private val TextSecondaryLight = Color(0x80000000)
private val TextSecondaryDark = Color(0xB3FFFFFF)
private val TextTertiaryLight = Color(0x66000000)
private val TextTertiaryDark = Color(0x80FFFFFF)

// Glass morphism colors
private val GlassWhiteLight = Color(0xBFFFFFFF)
private val GlassWhiteDark = Color(0xCC1C1C1C)
private val GlassWhiteSubtleLight = Color(0x8CFFFFFF)
private val GlassWhiteSubtleDark = Color(0x661C1C1C)
private val GlassBorderLight = Color(0x80FFFFFF)
private val GlassBorderDark = Color(0x80101010)

// Gradient colors
val Green50 = Color(0xFFF0FDF4)
val Yellow50 = Color(0xFFFEFCE8)
val Blue50 = Color(0xFFEFF6FF)
val Purple50 = Color(0xFFF5F3FF)

private val isDarkTheme: Boolean
    @Composable get() = MaterialTheme.colorScheme.background.luminance() < 0.5f

val TextPrimary: Color
    @Composable get() = if (isDarkTheme) TextPrimaryDark else TextPrimaryLight

val TextSecondary: Color
    @Composable get() = if (isDarkTheme) TextSecondaryDark else TextSecondaryLight

val TextTertiary: Color
    @Composable get() = if (isDarkTheme) TextTertiaryDark else TextTertiaryLight

val GlassWhite: Color
    @Composable get() = if (isDarkTheme) GlassWhiteDark else GlassWhiteLight

val GlassWhiteSubtle: Color
    @Composable get() = if (isDarkTheme) GlassWhiteSubtleDark else GlassWhiteSubtleLight

val GlassBorder: Color
    @Composable get() = if (isDarkTheme) GlassBorderDark else GlassBorderLight

