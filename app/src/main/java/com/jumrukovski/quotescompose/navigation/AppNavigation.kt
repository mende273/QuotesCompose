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
import androidx.navigation.toRoute
import com.jumrukovski.quotescompose.domain.model.Quote
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
import kotlin.reflect.typeOf

@Composable
fun AppNavigation(
    activity: ComponentActivity,
    innerPaddingValues: PaddingValues,
    navHostController: NavHostController
) {
    NavHost(
        navHostController,
        startDestination = Screen.Home
    ) {
        composable<Screen.Favourites> {
            val viewModel: FavouritesViewModel by activity.viewModels()
            FavouritesScreen(
                modifier = Modifier.padding(bottom = innerPaddingValues.calculateBottomPadding()),
                viewModel = viewModel,
                onNavigateToQuoteDetails = {
                    navHostController.navigate(Screen.QuoteDetail(it))
                }
            )
        }

        composable<Screen.QuoteDetail>(
            typeMap = mapOf(typeOf<Quote>() to QuoteNavType.QuoteType)
        ) { backStackEntry ->
            val arguments = backStackEntry.toRoute<Screen.QuoteDetail>()
            val viewModel: QuoteDetailViewModel by activity.viewModels()
            QuoteDetailScreen(
                viewModel = viewModel,
                quote = arguments.quote,
                onNavigateBack = {
                    navHostController.navigateUp()
                }
            )
        }

        composable<Screen.RandomQuote> {
            val viewModel: RandomQuoteViewModel by activity.viewModels()
            RandomQuoteScreen(viewModel, onNavigateBack = {
                navHostController.navigateUp()
            })
        }

        composable<Screen.QuoteOfTheDay> {
            val viewModel: QuoteOfTheDayViewModel by activity.viewModels()
            QuoteOfTheDayScreen(viewModel)
        }

        composable<Screen.Home> {
            val viewModel: HomeViewModel by activity.viewModels()
            HomeScreen(
                modifier = Modifier.padding(bottom = innerPaddingValues.calculateBottomPadding()),
                viewModel = viewModel,
                onNavigateToQuoteDetails = { quote ->
                    navHostController.navigate(Screen.QuoteDetail(quote))
                },
                onNavigateToRandomQuote = {
                    navHostController.navigate(Screen.RandomQuote)
                }
            )
        }
    }
}
