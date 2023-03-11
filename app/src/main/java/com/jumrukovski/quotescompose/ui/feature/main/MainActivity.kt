package com.jumrukovski.quotescompose.ui.feature.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.jumrukovski.quotescompose.ui.feature.search.SearchActivity
import com.jumrukovski.quotescompose.ui.theme.PrimaryBackgroundColor
import com.jumrukovski.quotescompose.ui.theme.QuotesComposeTheme

class MainActivity : ComponentActivity() {

    @Composable
    private fun HomeScreen(navHostController: NavHostController) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.PrimaryBackgroundColor)
        ) {
            Text(text = "Home screen")
        }
    }

    @Composable
    private fun CategoriesScreen(navHostController: NavHostController) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.PrimaryBackgroundColor)
        ) {
            Text(text = "Categories screen")
        }
    }

    @Composable
    private fun FavouritesScreen(navHostController: NavHostController) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.PrimaryBackgroundColor)
        ) {
            Text(text = "Favourites screen")
        }
    }

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
                        NavHost(
                            navController,
                            startDestination = Screen.Home.route,
                            Modifier.padding(innerPadding)
                        ) {
                            composable(Screen.Home.route) { HomeScreen(navController) }
                            composable(Screen.Categories.route) { CategoriesScreen(navController) }
                            composable(Screen.Favourites.route) { FavouritesScreen(navController) }
                        }
                    },
                    bottomBar = {
                        BottomNavigationBar(navController)
                    })
            }
        }
    }
}