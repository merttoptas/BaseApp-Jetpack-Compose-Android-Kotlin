package com.merttoptas.composebase.features.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = PaleViolet,
    onPrimary = VeryDarkViolet,
    secondary = LightGrayishViolet90,
    onSecondary = VeryDarkGrayishViolet40,
    error = VerySoftRed,
    onError = VeryDarkRed,
    background = VeryDarkBlue,
    onBackground = LightGrayishMagenta,
    surface = VeryDarkBlue,
    onSurface = LightGrayishMagenta,
)

private val LightColorPalette = lightColors(
    primary = DarkModerateViolet,
    onPrimary = White,
    secondary = VeryDarkGrayishViolet,
    onSecondary = LightGrayishViolet,
    error = StrongRed,
    onError = White,
    background = PaleGray,
    onBackground = VeryDarkBlue,
    surface = White,
    onSurface = White,
)

@Composable
fun ComposeBaseTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = AppTypography,
        shapes = Shapes,
        content = content
    )
}