package com.jumrukovski.quotescompose.navigation

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jumrukovski.quotescompose.ui.screen.detail.QuoteDetailScreen
import com.jumrukovski.quotescompose.ui.screen.favourites.FavouritesScreen
import com.jumrukovski.quotescompose.ui.screen.home.HomeScreen
import com.jumrukovski.quotescompose.ui.screen.home.HomeViewModel
import com.jumrukovski.quotescompose.ui.screen.random.RandomQuoteScreen
import com.jumrukovski.quotescompose.ui.screen.random.RandomQuoteViewModel
import com.jumrukovski.quotescompose.ui.screen.tags.TagsScreen
import com.jumrukovski.quotescompose.ui.screen.tags.TagsViewModel
import com.jumrukovski.quotescompose.ui.screen.tags.selected.SelectedTagScreen
import com.jumrukovski.quotescompose.ui.screen.tags.selected.SelectedTagViewModel

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
        composable(Screen.Tags.route) {
            val viewModel: TagsViewModel by activity.viewModels()
            TagsScreen(viewModel) {
                navHostController.navigate(ScreenWithArguments.SelectedTag.getRouteWith(it)) {
                    launchSingleTop = true
                }
            }
        }

        composable(Screen.Favourites.route) { FavouritesScreen(navHostController) }

        composable(
            route = ScreenWithArguments.QuoteDetail.initializeRoute(),
            arguments = ScreenWithArguments.QuoteDetail.arguments()
        ) { backStackEntry ->
            val bundleArguments = backStackEntry.arguments

            val id = bundleArguments?.getString(ScreenWithArguments.QuoteDetail.ARGUMENT_ID)
            val content = bundleArguments?.getString(ScreenWithArguments.QuoteDetail.ARGUMENT_CONTENT)
            val author = bundleArguments?.getString(ScreenWithArguments.QuoteDetail.ARGUMENT_AUTHOR)

            if (id != null && content != null && author != null) {
                QuoteDetailScreen(
                    id = id,
                    content = content,
                    author = author,
                    onNavigateBack = {
                        navHostController.navigateUp()
                    })
            }
        }

        composable(
            route = ScreenWithArguments.SelectedTag.initializeRoute(),
            arguments = ScreenWithArguments.SelectedTag.arguments()
        ) { backStackEntry ->
            val tagName: String = backStackEntry.arguments?.getString(
                ScreenWithArguments.SelectedTag.ARGUMENT_TAG_NAME, ""
            ) ?: ""
            val viewModel: SelectedTagViewModel by activity.viewModels()
            SelectedTagScreen(viewModel = viewModel,
                tagName = tagName,
                onNavigateToQuoteDetails = {
                    navHostController.navigate(route = ScreenWithArguments.QuoteDetail.getRouteWith(it._id,it.content,it.author)) {
                        launchSingleTop = true
                    }
                }, onNavigateBack = {
                    navHostController.navigateUp()
                })
        }

        composable(Screen.RandomQuote.route) {
            val viewModel: RandomQuoteViewModel by activity.viewModels()
            RandomQuoteScreen(viewModel, onNavigateBack = {
                navHostController.popBackStack()
            })
        }

        composable(Screen.Home.route) {
            val viewModel: HomeViewModel by activity.viewModels()
            HomeScreen(viewModel = viewModel, onNavigateToQuoteDetails = { quote ->
                navHostController.navigate(route = ScreenWithArguments.QuoteDetail.getRouteWith(quote._id,quote.content,quote.author)) {
                    launchSingleTop = true
                }
            }, onNavigateToRandomQuote = {
                navHostController.navigate(Screen.RandomQuote.route)
            })
        }
    }
}