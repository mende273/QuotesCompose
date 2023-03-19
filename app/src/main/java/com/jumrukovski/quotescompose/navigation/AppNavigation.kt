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
import com.jumrukovski.quotescompose.ui.screen.categories.CategoriesScreen
import com.jumrukovski.quotescompose.ui.screen.categories.CategoriesViewModel
import com.jumrukovski.quotescompose.ui.screen.categories.details.CategoryItemsScreen
import com.jumrukovski.quotescompose.ui.screen.categories.details.CategoryItemsViewModel
import com.jumrukovski.quotescompose.ui.screen.detail.QuoteDetailScreen
import com.jumrukovski.quotescompose.ui.screen.favourites.FavouritesScreen
import com.jumrukovski.quotescompose.ui.screen.home.HomeScreen

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
        composable(Screen.Home.route) { HomeScreen(){
            //navigate to quote detail screen
        } }
        composable(Screen.Categories.route) {
            val viewModel: CategoriesViewModel by activity.viewModels()
            CategoriesScreen(viewModel) {
                navHostController.navigate(ScreenWithArgument.CategoryDetail.getRouteWithArgument(it)) {
                    launchSingleTop = true
                }
            }
        }
        composable(Screen.Favourites.route) { FavouritesScreen(navHostController) }
        composable(ScreenWithArgument.QuoteDetail.route) {
            val bundleArguments = navHostController.previousBackStackEntry?.arguments
           val quote = if (Build.VERSION.SDK_INT >= 33) {
               bundleArguments?.getParcelable(ScreenWithArgument.QuoteDetail.argument,QuoteDTO::class.java)
            } else {
               bundleArguments?.getParcelable(ScreenWithArgument.QuoteDetail.argument)
            }
            QuoteDetailScreen(quote)
        }
        composable(ScreenWithArgument.CategoryDetail.route) { backStackEntry ->
            val category: String = backStackEntry.arguments?.getString(
                ScreenWithArgument.CategoryDetail.argument, "") ?: ""
            val viewModel: CategoryItemsViewModel by activity.viewModels()
            CategoryItemsScreen(viewModel = viewModel, categoryName = category) {
                navHostController.currentBackStackEntry?.arguments?.putParcelable(ScreenWithArgument.QuoteDetail.argument, it)
                navHostController.navigate(ScreenWithArgument.QuoteDetail.route) {
                    launchSingleTop = true
                }
            }
        }
    }
}