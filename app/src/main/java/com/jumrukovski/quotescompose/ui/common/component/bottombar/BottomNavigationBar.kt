package com.jumrukovski.quotescompose.ui.common.component.bottombar

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.jumrukovski.quotescompose.ui.theme.MediumDarkGreyColor
import com.jumrukovski.quotescompose.ui.theme.NavigationBarBackgroundColor
import com.jumrukovski.quotescompose.ui.theme.NavigationBarItemRippleColor
import com.jumrukovski.quotescompose.ui.theme.NavigationBarSelectedItemColor


@Composable
fun BottomNavigationBar(
    navHostController: NavHostController,
    bottomNavigationItems: Array<BottomNavigationItem>
) {
    NavigationBar(
        modifier = Modifier,
        contentColor = MaterialTheme.colorScheme.NavigationBarItemRippleColor,
        tonalElevation = 0.dp,
        containerColor = MaterialTheme.colorScheme.NavigationBarBackgroundColor,
        content = {
            val navBackStackEntry by navHostController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            bottomNavigationItems.forEach { screen ->
                NavigationBarItem(
                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                    onClick = {
                        navHostController.navigate(screen.route) {
                            popUpTo(navHostController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
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
                        selectedIconColor = MaterialTheme.colorScheme.NavigationBarSelectedItemColor,
                        unselectedIconColor = MediumDarkGreyColor,
                        selectedTextColor = MaterialTheme.colorScheme.NavigationBarSelectedItemColor,
                        unselectedTextColor = MediumDarkGreyColor,
                        indicatorColor = MaterialTheme.colorScheme.NavigationBarBackgroundColor
                    )
                )
            }
        }
    )
}