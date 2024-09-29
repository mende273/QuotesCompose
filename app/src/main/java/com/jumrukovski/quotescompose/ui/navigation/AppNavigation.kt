package com.jumrukovski.quotescompose.ui.navigation

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jumrukovski.quotescompose.ui.feature.detail.QuoteDetailScreen
import com.jumrukovski.quotescompose.ui.feature.detail.QuoteDetailViewModel
import com.jumrukovski.quotescompose.ui.feature.favourites.FavouritesScreen
import com.jumrukovski.quotescompose.ui.feature.favourites.FavouritesViewModel
import com.jumrukovski.quotescompose.ui.feature.home.HomeScreen
import com.jumrukovski.quotescompose.ui.feature.home.HomeViewModel
import com.jumrukovski.quotescompose.ui.feature.random.RandomQuoteScreen
import com.jumrukovski.quotescompose.ui.feature.random.RandomQuoteViewModel
import com.jumrukovski.quotescompose.ui.feature.today.QuoteOfTheDayScreen
import com.jumrukovski.quotescompose.ui.feature.today.QuoteOfTheDayViewModel

@Composable
fun AppNavigation(
    activity: ComponentActivity,
    navHostController: NavHostController
) {
    NavHost(
        navHostController,
        startDestination = Screen.WithoutArguments.Home.route
    ) {
        composable(Screen.WithoutArguments.Favourites.route) {
            val viewModel: FavouritesViewModel by activity.viewModels()
            FavouritesScreen(
                viewModel = viewModel,
                onNavigateToQuoteDetails = {
                    navHostController.singleTopNavigate(
                        route = Screen.WithArguments.QuoteDetail.getRouteWithArguments(
                            it.id.toString(),
                            it.content,
                            it.author
                        )
                    )
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
                    id = id.toInt(),
                    content = content,
                    author = author,
                    onNavigateBack = {
                        navHostController.navigateUp()
                    }
                )
            }
        }

        composable(Screen.WithoutArguments.RandomQuote.route) {
            val viewModel: RandomQuoteViewModel by activity.viewModels()
            RandomQuoteScreen(viewModel, onNavigateBack = {
                navHostController.popBackStack()
            })
        }

        composable(Screen.WithoutArguments.QuoteOfTheDay.route) {
            val viewModel: QuoteOfTheDayViewModel by activity.viewModels()
            QuoteOfTheDayScreen(viewModel)
        }

        composable(Screen.WithoutArguments.Home.route) {
            val viewModel: HomeViewModel by activity.viewModels()
            HomeScreen(
                viewModel = viewModel,
                onNavigateToQuoteDetails = { quote ->
                    navHostController.singleTopNavigate(
                        route = Screen.WithArguments.QuoteDetail.getRouteWithArguments(
                            quote.id.toString(),
                            quote.content,
                            quote.author
                        )
                    )
                },
                onNavigateToRandomQuote = {
                    navHostController.navigate(Screen.WithoutArguments.RandomQuote.route)
                }
            )
        }
    }
}

private fun NavHostController.singleTopNavigate(route: String) {
    this.navigate(route) {
        launchSingleTop = true
    }
}
