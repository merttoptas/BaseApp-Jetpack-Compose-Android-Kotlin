package com.merttoptas.composebase.features.ui.theme

import android.app.Activity
import android.os.Build
import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorPalette = darkColorScheme(
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

private val LightColorPalette = lightColorScheme(
    primary = DarkModerateViolet,
    onPrimary = White,
    secondary = VeryDarkGrayishViolet,
    onSecondary = LightGrayishViolet,
    error = StrongRed,
    onError = White,
    background = PaleGray,
    onBackground = VeryDarkBlue,
    surface = White,
    onSurface = LightGrayishMagenta,
    onSurfaceVariant = White
)

@Composable
fun ComposeBaseTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorPalette
        else -> LightColorPalette
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val currentWindow = (view.context as? Activity)?.window
                ?: throw Exception("Not in an activity - unable to get Window reference")

            (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(currentWindow,view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}