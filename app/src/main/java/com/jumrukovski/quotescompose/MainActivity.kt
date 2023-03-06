package com.jumrukovski.quotescompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jumrukovski.quotescompose.ui.theme.*

class MainActivity : ComponentActivity() {

    @Composable
    private fun Toolbar(onActionClick: () -> Unit = {}) {
        SmallTopAppBar(
            modifier = Modifier.background(MaterialTheme.colorScheme.PrimaryBackgroundColor),
            title = { Text(stringResource(R.string.app_name)) },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.PrimaryBackgroundColor,
                scrolledContainerColor = MaterialTheme.colorScheme.PrimaryBackgroundColor,
                navigationIconContentColor = MaterialTheme.colorScheme.PrimaryTextColor,
                titleContentColor = MaterialTheme.colorScheme.PrimaryTextColor,
                actionIconContentColor = MaterialTheme.colorScheme.PrimaryTextColor
            ),
            actions = {
                IconButton(onClick = onActionClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_search_24),
                        contentDescription = stringResource(id = R.string.action_search),
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        )
    }

    @Composable
    private fun BottomNavigationBar(content: @Composable RowScope.() -> Unit) {
        NavigationBar(
            modifier = Modifier,
            contentColor = MaterialTheme.colorScheme.NavigationBarItemRippleColor,
            tonalElevation = 0.dp,
            content = content,
            containerColor = MaterialTheme.colorScheme.NavigationBarBackgroundColor
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuotesComposeTheme {
                Scaffold(
                    topBar = {
                        Toolbar(onActionClick = {
                            //todo search icon clicked
                        })
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
                        BottomNavigationBar {
                            NavigationBarItem(
                                selected = true,
                                onClick = { null },
                                label = { Text(text = "test") },
                                enabled = true,
                                icon = {Icon(
                                    painter = painterResource(id = R.drawable.baseline_search_24),
                                    contentDescription = "item"
                                )},
                                alwaysShowLabel = true,
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = MaterialTheme.colorScheme.NavigationBarSelectedItemColor,
                                    unselectedIconColor = MediumDarkGreyColor,
                                    selectedTextColor = MaterialTheme.colorScheme.NavigationBarSelectedItemColor,
                                    unselectedTextColor = MediumDarkGreyColor,
                                    indicatorColor = MaterialTheme.colorScheme.NavigationBarBackgroundColor
                                )
                            )
                            NavigationBarItem(
                                selected = false,
                                onClick = { null },
                                label = { Text(text = "test") },
                                enabled = true,
                                icon = {Icon(
                                    painter = painterResource(id = R.drawable.baseline_search_24),
                                    contentDescription = "item"
                                )},
                                alwaysShowLabel = true,
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = MaterialTheme.colorScheme.NavigationBarSelectedItemColor,
                                    unselectedIconColor = MediumDarkGreyColor,
                                    selectedTextColor = MaterialTheme.colorScheme.NavigationBarSelectedItemColor,
                                    unselectedTextColor = MediumDarkGreyColor,
                                    indicatorColor = MaterialTheme.colorScheme.PrimaryBackgroundColor
                                )
                            )
                            NavigationBarItem(
                                selected = false,
                                onClick = { null },
                                label = { Text(text = "test") },
                                enabled = true,
                                icon = {Icon(
                                    painter = painterResource(id = R.drawable.baseline_search_24),
                                    contentDescription = "item"
                                )},
                                alwaysShowLabel = true,
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = MaterialTheme.colorScheme.NavigationBarSelectedItemColor,
                                    unselectedIconColor = MediumDarkGreyColor,
                                    selectedTextColor = MaterialTheme.colorScheme.NavigationBarSelectedItemColor,
                                    unselectedTextColor = MediumDarkGreyColor,
                                    indicatorColor = MaterialTheme.colorScheme.PrimaryBackgroundColor
                                )
                            )
                        }
                    })
            }
        }
    }
}