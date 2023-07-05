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
import com.jumrukovski.quotescompose.ui.screen.detail.QuoteDetailViewModel
import com.jumrukovski.quotescompose.ui.screen.favourites.FavouritesScreen
import com.jumrukovski.quotescompose.ui.screen.favourites.FavouritesViewModel
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
        startDestination = Screen.WithoutArguments.Home.route,
        Modifier.padding(innerPadding)
    ) {
        composable(Screen.WithoutArguments.Tags.route) {
            val viewModel: TagsViewModel by activity.viewModels()
            TagsScreen(viewModel) {
                navHostController.navigate(Screen.WithArguments.SelectedTag.getRouteWithArguments(it)) {
                    launchSingleTop = true
                }
            }
        }

        composable(Screen.WithoutArguments.Favourites.route) {
            val viewModel: FavouritesViewModel by activity.viewModels()
            FavouritesScreen(
                viewModel,
                onNavigateToQuoteDetails = {
                    navHostController.navigate(
                        route = Screen.WithArguments.QuoteDetail.getRouteWithArguments(
                            it.id,
                            it.content,
                            it.author
                        )
                    ) {
                        launchSingleTop = true
                    }
                },
                onNavigateBack = {
                    navHostController.navigateUp()
                }
            )
        }

        composable(
            route = Screen.WithArguments.QuoteDetail.route,
            arguments = Screen.WithArguments.QuoteDetail.getNavArguments()
        ) { backStackEntry ->
            val bundleArguments = backStackEntry.arguments

            val id = bundleArguments?.getString(Screen.WithArguments.QuoteDetail.ARGUMENT_ID)
            val content =
                bundleArguments?.getString(Screen.WithArguments.QuoteDetail.ARGUMENT_CONTENT)
            val author =
                bundleArguments?.getString(Screen.WithArguments.QuoteDetail.ARGUMENT_AUTHOR)

            if (id != null && content != null && author != null) {
                val viewModel: QuoteDetailViewModel by activity.viewModels()
                QuoteDetailScreen(
                    viewModel = viewModel,
                    id = id,
                    content = content,
                    author = author,
                    onNavigateBack = {
                        navHostController.navigateUp()
                    }
                )
            }
        }

        composable(
            route = Screen.WithArguments.SelectedTag.route,
            arguments = Screen.WithArguments.SelectedTag.getNavArguments()
        ) { backStackEntry ->
            val tagName: String = backStackEntry.arguments?.getString(
                Screen.WithArguments.SelectedTag.ARGUMENT_TAG_NAME,
                ""
            ) ?: ""
            val viewModel: SelectedTagViewModel by activity.viewModels()
            SelectedTagScreen(
                viewModel = viewModel,
                tagName = tagName,
                onNavigateToQuoteDetails = {
                    navHostController.navigate(
                        route = Screen.WithArguments.QuoteDetail.getRouteWithArguments(
                            it.id,
                            it.content,
                            it.author
                        )
                    ) {
                        launchSingleTop = true
                    }
                },
                onNavigateBack = {
                    navHostController.navigateUp()
                }
            )
        }

        composable(Screen.WithoutArguments.RandomQuote.route) {
            val viewModel: RandomQuoteViewModel by activity.viewModels()
            RandomQuoteScreen(viewModel, onNavigateBack = {
                navHostController.popBackStack()
            })
        }

        composable(Screen.WithoutArguments.Home.route) {
            val viewModel: HomeViewModel by activity.viewModels()
            HomeScreen(viewModel = viewModel, onNavigateToQuoteDetails = { quote ->
                navHostController.navigate(
                    route = Screen.WithArguments.QuoteDetail.getRouteWithArguments(
                        quote.id,
                        quote.content,
                        quote.author
                    )
                ) {
                    launchSingleTop = true
                }
            }, onNavigateToRandomQuote = {
                    navHostController.navigate(Screen.WithoutArguments.RandomQuote.route)
                })
        }
    }
}
