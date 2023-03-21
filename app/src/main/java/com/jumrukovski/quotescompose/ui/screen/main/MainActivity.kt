package com.jumrukovski.quotescompose.ui.screen.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.jumrukovski.quotescompose.R
import com.jumrukovski.quotescompose.navigation.AppNavigation
import com.jumrukovski.quotescompose.ui.common.BottomNavigationBar
import com.jumrukovski.quotescompose.ui.common.Toolbar
import com.jumrukovski.quotescompose.ui.theme.QuotesComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
                        ){
                            when(it){
                                MainScreenMenuItem.RANDOM -> ""//todo
                            }
                        }
                    },
                    content = { innerPadding ->
                        AppNavigation(this@MainActivity,
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
}