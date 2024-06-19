package com.jumrukovski.quotescompose.ui.feature.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jumrukovski.quotescompose.ui.common.component.bottombar.BottomNavigationBar
import com.jumrukovski.quotescompose.ui.common.component.bottombar.BottomNavigationItem
import com.jumrukovski.quotescompose.ui.navigation.AppNavigation
import com.jumrukovski.quotescompose.ui.navigation.Screen
import com.jumrukovski.quotescompose.ui.theme.QuotesComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bottomNavigationItems = enumValues<BottomNavigationItem>()

        setContent {
            val navController = rememberNavController()

            var isNavigationBarVisible by remember { mutableStateOf(false) }

            isNavigationBarVisible = isRouteFromBottomBarMenu(
                navController.currentBackStackEntryAsState()
            )

            QuotesComposeTheme {
                Scaffold(
                    content = { innerPadding ->
                        AppNavigation(
                            this@MainActivity,
                            navHostController = navController,
                            innerPadding = innerPadding
                        )
                    },
                    bottomBar = {
                        AnimatedVisibility(
                            visible = isNavigationBarVisible,
                            enter = slideInVertically(initialOffsetY = { it }),
                            exit = slideOutVertically(targetOffsetY = { it })
                        ) {
                            BottomNavigationBar(navController, bottomNavigationItems)
                        }
                    }
                )
            }
        }
    }
}

private fun isRouteFromBottomBarMenu(currentBackStackEntry: State<NavBackStackEntry?>): Boolean {
    val currentRoute = currentBackStackEntry.value?.destination?.route
    return currentRoute?.let { route ->
        return@let (
            route == Screen.WithoutArguments.Home.route ||
                route == Screen.WithoutArguments.Tags.route ||
                route == Screen.WithoutArguments.Favourites.route
            )
    } ?: false
}
