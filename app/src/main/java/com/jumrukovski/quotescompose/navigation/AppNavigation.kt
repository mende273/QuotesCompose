package com.jumrukovski.quotescompose.navigation

import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jumrukovski.quotescompose.data.model.QuoteDTO
import com.jumrukovski.quotescompose.ui.screen.tags.TagsScreen
import com.jumrukovski.quotescompose.ui.screen.tags.TagsViewModel
import com.jumrukovski.quotescompose.ui.screen.tags.selected.SelectedTagScreen
import com.jumrukovski.quotescompose.ui.screen.tags.selected.SelectedTagViewModel
import com.jumrukovski.quotescompose.ui.screen.detail.QuoteDetailScreen
import com.jumrukovski.quotescompose.ui.screen.favourites.FavouritesScreen
import com.jumrukovski.quotescompose.ui.screen.home.HomeScreen
import com.jumrukovski.quotescompose.ui.screen.random.RandomQuoteScreen

@Composable
fun AppNavigation(
    activity: ComponentActivity,
    navHostController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        navHostController,
        startDestination = Screen.Home.route,
        Modifier.padding(innerPadding)
    ) {
        composable(Screen.Home.route) {
            HomeScreen(onNavigateToQuoteDetails = {
                //todo navigate to quote details
            }, onNavigateToRandomQuote = {
                navHostController.navigate(Screen.RandomQuote.route)
            })
        }
        composable(Screen.Tags.route) {
            val viewModel: TagsViewModel by activity.viewModels()
            TagsScreen(viewModel) {
                navHostController.navigate(ScreenWithArgument.SelectedTag.getRouteWithArgument(it)) {
                    launchSingleTop = true
                }
            }
        }
        composable(Screen.Favourites.route) { FavouritesScreen(navHostController) }
        composable(ScreenWithArgument.QuoteDetail.route) {
            val bundleArguments = navHostController.previousBackStackEntry?.arguments
            val quote = if (Build.VERSION.SDK_INT >= 33) {
                bundleArguments?.getParcelable(
                    ScreenWithArgument.QuoteDetail.argument,
                    QuoteDTO::class.java
                )
            } else {
                bundleArguments?.getParcelable(ScreenWithArgument.QuoteDetail.argument)
            }
            QuoteDetailScreen(quoteDTO = quote, onNavigateBack = {
                navHostController.popBackStack()
            })
        }
        composable(ScreenWithArgument.SelectedTag.route) { backStackEntry ->
            val tagName: String = backStackEntry.arguments?.getString(
                ScreenWithArgument.SelectedTag.argument, ""
            ) ?: ""
            val viewModel: SelectedTagViewModel by activity.viewModels()
            SelectedTagScreen(viewModel = viewModel, tagName = tagName) {
                navHostController.currentBackStackEntry?.arguments?.putParcelable(
                    ScreenWithArgument.QuoteDetail.argument,
                    it
                )
                navHostController.navigate(ScreenWithArgument.QuoteDetail.route) {
                    launchSingleTop = true
                }
            }
        }
        composable(Screen.RandomQuote.route) {
            RandomQuoteScreen()
        }
    }
}