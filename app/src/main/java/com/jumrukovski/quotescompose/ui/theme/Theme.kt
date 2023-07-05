package com.jumrukovski.quotescompose.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorScheme = darkColorScheme(
    primary = BlackColor,
    secondary = GreyColor,
    tertiary = TertiaryColorDarkTheme
)

private val LightColorScheme = lightColorScheme(
    primary = GreyColor,
    secondary = BlackColor,
    tertiary = TertiaryColorLightTheme

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

val ColorScheme.TertiaryColor: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) TertiaryColorLightTheme else TertiaryColorDarkTheme

val ColorScheme.PrimaryTextColor: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) BlackColor else GreyColor

val ColorScheme.PrimaryBackgroundColor: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) GreyColor else BlackColor

val ColorScheme.NavigationBarBackgroundColor: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) WhiteColor else DarkGreyColor

val ColorScheme.NavigationBarSelectedItemColor: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) BlackColor else WhiteColor

val ColorScheme.NavigationBarItemRippleColor: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) NavigationItemRippleColorLightTheme else NavigationItemRippleColorDarkTheme

@Composable
fun QuotesComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
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
        val systemUiController = rememberSystemUiController()

        SideEffect {
            with(systemUiController) {
                setStatusBarColor(color = if (darkTheme) BlackColor else GreyColor)
                setNavigationBarColor(color = if (darkTheme) DarkGreyColor else WhiteColor)
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
