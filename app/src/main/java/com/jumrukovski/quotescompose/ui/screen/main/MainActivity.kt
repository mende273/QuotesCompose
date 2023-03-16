package com.jumrukovski.quotescompose.ui.screen.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Scaffold
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.jumrukovski.quotescompose.R
import com.jumrukovski.quotescompose.TagsViewModel
import com.jumrukovski.quotescompose.navigation.AppNavigation
import com.jumrukovski.quotescompose.ui.common.BottomNavigationBar
import com.jumrukovski.quotescompose.ui.common.Toolbar
import com.jumrukovski.quotescompose.ui.screen.search.SearchActivity
import com.jumrukovski.quotescompose.ui.theme.QuotesComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    //TODO create screen tags

    //TODO place here just for test
    private val viewModel: TagsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //TODO just for test
        lifecycleScope.launch {
            viewModel.getQuotesForTag("love")
        }

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
                        AppNavigation(
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