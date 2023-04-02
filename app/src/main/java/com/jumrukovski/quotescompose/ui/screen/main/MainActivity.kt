package com.jumrukovski.quotescompose.ui.screen.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.State
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jumrukovski.quotescompose.navigation.AppNavigation
import com.jumrukovski.quotescompose.navigation.Screen
import com.jumrukovski.quotescompose.ui.common.bottombar.BottomNavigationBar
import com.jumrukovski.quotescompose.ui.common.bottombar.BottomNavigationItem
import com.jumrukovski.quotescompose.ui.theme.QuotesComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bottomNavigationItems = enumValues<BottomNavigationItem>()

        setContent {
            val navController = rememberNavController()

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
                        if (isCurrentRouteFromBottomBarMenu(navController.currentBackStackEntryAsState())) {
                            BottomNavigationBar(navController,bottomNavigationItems)
                        }
                    })
            }
        }
    }
}

private fun isCurrentRouteFromBottomBarMenu(currentBackStackEntry: State<NavBackStackEntry?>): Boolean {
    val currentRoute = currentBackStackEntry.value?.destination?.route
    return currentRoute?.let { route ->
        return@let (route == Screen.WithoutArguments.Home.route
                || route == Screen.WithoutArguments.Tags.route
                || route == Screen.WithoutArguments.Favourites.route)
    } ?: false
}