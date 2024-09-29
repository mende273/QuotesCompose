package com.jumrukovski.quotescompose.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val darkColorScheme = darkColorScheme(
    primary = BlackColor,
    secondary = GreyColor,
    tertiary = TertiaryColorDarkTheme,
    background = BlackColor
)

private val lightColorScheme = lightColorScheme(
    primary = GreyColor,
    secondary = BlackColor,
    tertiary = TertiaryColorLightTheme,
    background = GreyColor
)

val TertiaryColor: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) TertiaryColorLightTheme else TertiaryColorDarkTheme

val PrimaryTextColor: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) BlackColor else GreyColor

val PrimaryBackgroundColor: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) GreyColor else BlackColor

val NavigationBarBackgroundColor: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) WhiteColor else DarkGreyColor

val NavigationBarSelectedColor: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) BlackColor else WhiteColor

val NavigationBarItemRippleColor: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) {
        NavigationItemRippleColorLightTheme
    } else {
        NavigationItemRippleColorDarkTheme
    }

@Composable
fun QuotesComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> darkColorScheme
        else -> lightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window

            val windowsInsetsController = WindowCompat.getInsetsController(window, view)
            windowsInsetsController.isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
