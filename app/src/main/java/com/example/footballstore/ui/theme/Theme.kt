package com.example.footballstore.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = FieldGreen,
    onPrimary = ChalkWhite,
    primaryContainer = LineGreen,
    onPrimaryContainer = DarkTurf,
    secondary = CarbonBlack,
    onSecondary = ChalkWhite,
    tertiary = GrassGreen,
    onTertiary = ChalkWhite,
    background = ChalkWhite,
    onBackground = CarbonBlack,
    surface = ChalkWhite,
    onSurface = CarbonBlack,
    surfaceVariant = LineGreen,
    onSurfaceVariant = GoalGray,
    error = DangerRed
)

private val DarkColorScheme = darkColorScheme(
    primary = GrassGreen,
    onPrimary = CarbonBlack,
    primaryContainer = DarkTurf,
    onPrimaryContainer = ChalkWhite,
    secondary = ChalkWhite,
    onSecondary = CarbonBlack,
    tertiary = FieldGreen,
    onTertiary = ChalkWhite,
    background = CarbonBlack,
    onBackground = ChalkWhite,
    surface = DarkTurf,
    onSurface = ChalkWhite,
    surfaceVariant = FieldGreen,
    onSurfaceVariant = LineGreen,
    error = DangerRed
)

@Composable
fun FootballStoreTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}
