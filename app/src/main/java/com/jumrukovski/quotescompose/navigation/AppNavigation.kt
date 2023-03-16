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
import com.jumrukovski.quotescompose.ui.screen.categories.CategoriesScreen
import com.jumrukovski.quotescompose.ui.screen.categories.CategoriesViewModel
import com.jumrukovski.quotescompose.ui.screen.detail.QuoteDetailScreen
import com.jumrukovski.quotescompose.ui.screen.favourites.FavouritesScreen
import com.jumrukovski.quotescompose.ui.screen.home.HomeScreen

@Composable
fun AppNavigation(activity: ComponentActivity,navHostController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navHostController,
        startDestination = Screen.Home.route,
        Modifier.padding(innerPadding)
    ) {
        composable(Screen.Home.route) { HomeScreen(navHostController) }
        composable(Screen.Categories.route) {
            val viewModel: CategoriesViewModel by activity.viewModels()
            CategoriesScreen(navHostController,viewModel)
        }
        composable(Screen.Favourites.route) { FavouritesScreen(navHostController) }
        composable(Screen.QuoteDetail.route) { QuoteDetailScreen() }
    }
}