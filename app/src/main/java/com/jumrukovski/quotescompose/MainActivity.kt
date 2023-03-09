package com.jumrukovski.quotescompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.jumrukovski.quotescompose.ui.common.BottomNavigationBar
import com.jumrukovski.quotescompose.ui.common.Toolbar
import com.jumrukovski.quotescompose.ui.theme.PrimaryBackgroundColor
import com.jumrukovski.quotescompose.ui.theme.QuotesComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
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
                    content = { padding ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(padding)
                                .background(MaterialTheme.colorScheme.PrimaryBackgroundColor)
                        ) {
                            //todo content details
                        }
                    },
                    bottomBar = {
                        BottomNavigationBar { menuItem ->
                            //todo handle bottom menu item click
                        }
                    })
            }
        }
    }
}