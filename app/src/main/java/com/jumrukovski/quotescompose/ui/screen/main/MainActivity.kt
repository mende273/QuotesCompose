package com.jumrukovski.quotescompose.ui.screen.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.rememberNavController
import com.jumrukovski.quotescompose.navigation.AppNavigation
import com.jumrukovski.quotescompose.ui.common.BottomNavigationBar
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
                    content = { innerPadding ->
                        AppNavigation(
                            this@MainActivity,
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