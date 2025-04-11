package com.mlk.taskmanager.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.mlk.taskmanager.ui.theme.Background
import com.mlk.taskmanager.ui.theme.TextColor
import com.mlk.taskmanager.ui.theme.PrimaryColor
import com.mlk.taskmanager.ui.theme.SecondaryColor
import com.mlk.taskmanager.ui.theme.AccentColor

private val LightColorScheme = lightColorScheme(
    primary = PrimaryColor,
    onPrimary = TextColor,
    primaryContainer = PrimaryColor.copy(alpha = 0.7f),
    onPrimaryContainer = TextColor,
    secondary = SecondaryColor,
    onSecondary = Color.Black,
    secondaryContainer = SecondaryColor.copy(alpha = 0.7f),
    onSecondaryContainer = Color.Black,
    tertiary = AccentColor,
    onTertiary = TextColor,
    tertiaryContainer = AccentColor.copy(alpha = 0.7f),
    onTertiaryContainer = TextColor,
    background = Background,
    onBackground = TextColor,
    surface = Background,
    onSurface = TextColor,
    surfaceVariant = Background.copy(alpha = 0.7f),
    onSurfaceVariant = TextColor,
    outline = PrimaryColor
)

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryColor,
    onPrimary = TextColor,
    primaryContainer = PrimaryColor.copy(alpha = 0.7f),
    onPrimaryContainer = TextColor,
    secondary = SecondaryColor,
    onSecondary = Color.Black,
    secondaryContainer = SecondaryColor.copy(alpha = 0.7f),
    onSecondaryContainer = Color.Black,
    tertiary = AccentColor,
    onTertiary = TextColor,
    tertiaryContainer = AccentColor.copy(alpha = 0.7f),
    onTertiaryContainer = TextColor,
    background = Background,
    onBackground = TextColor,
    surface = Background,
    onSurface = TextColor,
    surfaceVariant = Background.copy(alpha = 0.7f),
    onSurfaceVariant = TextColor,
    outline = PrimaryColor
)

@Composable
fun TaskManagerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, 
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Background.toArgb() 
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false 
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}