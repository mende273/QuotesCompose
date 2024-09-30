package com.jumrukovski.quotescompose.ui.common.component.bottombar

import android.content.res.Configuration
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jumrukovski.quotescompose.navigation.Screen
import com.jumrukovski.quotescompose.ui.theme.MediumDarkGreyColor
import com.jumrukovski.quotescompose.ui.theme.NavigationBarBackgroundColor
import com.jumrukovski.quotescompose.ui.theme.NavigationBarItemRippleColor
import com.jumrukovski.quotescompose.ui.theme.NavigationBarSelectedColor

@Composable
fun BottomNavigationBar(
    currentScreen: Screen?,
    bottomNavigationItems: Array<BottomNavigationItem>,
    onNavigateToScreen: (Screen) -> Unit
) {
    NavigationBar(
        modifier = Modifier,
        contentColor = NavigationBarItemRippleColor,
        tonalElevation = 0.dp,
        containerColor = NavigationBarBackgroundColor,
        content = {
            bottomNavigationItems.forEach { screen ->
                NavigationBarItem(
                    selected = currentScreen == screen.route,
                    onClick = { onNavigateToScreen(screen.route) },
                    label = { Text(text = stringResource(id = screen.resourceId)) },
                    enabled = true,
                    icon = {
                        screen.icon?.let { painterResource(id = screen.icon) }?.let {
                            Icon(
                                painter = it,
                                contentDescription = stringResource(id = screen.resourceId)
                            )
                        }
                    },
                    alwaysShowLabel = true,
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = NavigationBarSelectedColor,
                        unselectedIconColor = MediumDarkGreyColor,
                        selectedTextColor = NavigationBarSelectedColor,
                        unselectedTextColor = MediumDarkGreyColor,
                        indicatorColor = NavigationBarBackgroundColor
                    )
                )
            }
        }
    )
}

@Preview
@Composable
private fun BottomNavigationBarPreview() {
    BottomNavigationBar(
        currentScreen = Screen.Home,
        bottomNavigationItems = enumValues<BottomNavigationItem>(),
        onNavigateToScreen = {}
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BottomNavigationBarDarkPreview() {
    BottomNavigationBar(
        currentScreen = Screen.Home,
        bottomNavigationItems = enumValues<BottomNavigationItem>(),
        onNavigateToScreen = {}
    )
}
