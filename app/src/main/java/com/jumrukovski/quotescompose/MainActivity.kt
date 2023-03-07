package com.jumrukovski.quotescompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.jumrukovski.quotescompose.ui.shared.BottomNavigationBar
import com.jumrukovski.quotescompose.ui.shared.Toolbar
import com.jumrukovski.quotescompose.ui.theme.PrimaryBackgroundColor
import com.jumrukovski.quotescompose.ui.theme.QuotesComposeTheme

class MainActivity : ComponentActivity() {

    //todo do it differently
    data class MainActivityMenuItem(val title: String, @DrawableRes val drawable: Int)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val topMenuItems = ArrayList<MainActivityMenuItem>()
        topMenuItems.add(
            MainActivityMenuItem(
                resources.getString(R.string.action_search),
                R.drawable.baseline_search_24
            )
        )
        setContent {
            QuotesComposeTheme {
                Scaffold(
                    topBar = {
                        Toolbar(stringResource(id = R.string.app_name), topMenuItems) { menuItem ->
                            //todo handle top menu item
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