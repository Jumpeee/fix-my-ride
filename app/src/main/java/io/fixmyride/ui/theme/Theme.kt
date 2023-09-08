package io.fixmyride.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun FixMyRideTheme(content: @Composable () -> Unit) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = ColorPalette.background.toArgb()
            window.navigationBarColor = ColorPalette.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    val colorScheme = darkColorScheme(
        primary = ColorPalette.primary,
        secondary = ColorPalette.secondary,
        tertiary = ColorPalette.tertiary,
        background = ColorPalette.background,
    )

    MaterialTheme(
        colorScheme = colorScheme,
        content = content,
    )
}