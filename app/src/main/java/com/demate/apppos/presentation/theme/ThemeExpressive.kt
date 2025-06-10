package com.demate.apppos.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun ThemeExpressive(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (useDarkTheme) {
        expressiveDarkColorScheme()
    } else {
        expressiveLightColorScheme()
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

@Composable
fun expressiveLightColorScheme(): ColorScheme {
    return lightColorScheme(
        primary = Color(0xFF0062A1),
        secondary = Color(0xFF545F70),
        tertiary = Color(0xFF6C5677),
    ).expressiveTonesMapping()
}

@Composable
fun expressiveDarkColorScheme(): ColorScheme {
    return darkColorScheme(
        primary = Color(0xFF89C3FF),
        secondary = Color(0xFFBBC7DB),
        tertiary = Color(0xFFD7BFE0),
    ).expressiveTonesMapping()
}

fun ColorScheme.expressiveTonesMapping(): ColorScheme {
    return copy(
        primaryContainer = primary.lighten(0.7f),
        secondaryContainer = secondary.lighten(0.7f),
        tertiaryContainer = tertiary.lighten(0.7f)
    )
}

fun Color.lighten(factor: Float): Color {
    return copy(
        red = red + (1f - red) * factor,
        green = green + (1f - green) * factor,
        blue = blue + (1f - blue) * factor
    )
}
