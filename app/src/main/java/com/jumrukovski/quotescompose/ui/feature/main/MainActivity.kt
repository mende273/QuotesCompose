package com.jumrukovski.quotescompose.ui.feature.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jumrukovski.quotescompose.navigation.AppNavigation
import com.jumrukovski.quotescompose.navigation.Screen
import com.jumrukovski.quotescompose.ui.common.component.bottombar.BottomNavigationBar
import com.jumrukovski.quotescompose.ui.common.component.bottombar.BottomNavigationItem
import com.jumrukovski.quotescompose.ui.theme.QuotesComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val bottomNavigationItems = enumValues<BottomNavigationItem>()

        setContent {
            val navController = rememberNavController()

            var isNavigationBarVisible by remember { mutableStateOf(false) }

            val currentNavigationBarDestination = navController
                .currentBackStackEntryAsState()
                .value
                .currentDestinationFromNavigationBar(
                    enumValues<BottomNavigationItem>()
                        .map { it.route }
                )

            isNavigationBarVisible = currentNavigationBarDestination != null

            QuotesComposeTheme {
                Scaffold(
                    content = {
                        AppNavigation(
                            this@MainActivity,
                            navHostController = navController
                        )
                    },
                    bottomBar = {
                        AnimatedVisibility(
                            visible = isNavigationBarVisible,
                            enter = slideInVertically(initialOffsetY = { it }),
                            exit = slideOutVertically(targetOffsetY = { it })
                        ) {
                            BottomNavigationBar(
                                currentNavigationBarDestination,
                                bottomNavigationItems,
                                onNavigateToScreen = { navController.singleTopNavigate(it) }
                            )
                        }
                    }
                )
            }
        }
    }
}

private fun NavBackStackEntry?.currentDestinationFromNavigationBar(
    navigationBarItems: List<Screen>
): Screen? {
    var currentDestination: Screen? = null

    navigationBarItems.forEach { item ->
        if (this?.destination?.hierarchy?.any {
            it.hasRoute(item::class)
        } == true
        ) {
            currentDestination = item
        }
    }

    return currentDestination
}

private fun NavHostController.singleTopNavigate(screen: Screen) {
    navigate(screen) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
