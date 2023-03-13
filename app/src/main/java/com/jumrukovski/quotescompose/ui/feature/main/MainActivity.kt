package com.jumrukovski.quotescompose.ui.feature.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jumrukovski.quotescompose.R
import com.jumrukovski.quotescompose.ui.common.BottomNavigationBar
import com.jumrukovski.quotescompose.ui.common.Screen
import com.jumrukovski.quotescompose.ui.common.Toolbar
import com.jumrukovski.quotescompose.ui.feature.categories.CategoriesScreen
import com.jumrukovski.quotescompose.ui.feature.detail.QuoteDetailScreen
import com.jumrukovski.quotescompose.ui.feature.favourites.FavouritesScreen
import com.jumrukovski.quotescompose.ui.feature.home.HomeScreen
import com.jumrukovski.quotescompose.ui.feature.search.SearchActivity
import com.jumrukovski.quotescompose.ui.theme.QuotesComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            QuotesComposeTheme {
                Scaffold(
                    topBar = {
                        Toolbar(
                            stringResource(id = R.string.app_name),
                            MainScreenMenuItem.values().asList()
                        ) { menuItem ->
                            if (menuItem == MainScreenMenuItem.SEARCH) {
                                startActivity(SearchActivity.newIntent(this@MainActivity))
                            }
                        }
                    },
                    content = { innerPadding ->
                        ContentNavigation(
                            navHostController = navController,
                            innerPadding = innerPadding
                        )
                    },
                    bottomBar = {
                        BottomNavigationBar(navController)
                    })
            }
        }
    }

    @Composable
    private fun ContentNavigation(navHostController: NavHostController,innerPadding:PaddingValues){
        NavHost(
            navHostController,
            startDestination = Screen.Home.route,
            Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { HomeScreen(navHostController) }
            composable(Screen.Categories.route) { CategoriesScreen(navHostController) }
            composable(Screen.Favourites.route) { FavouritesScreen(navHostController) }
            composable(Screen.QuoteDetail.route){QuoteDetailScreen()}
        }
    }
}