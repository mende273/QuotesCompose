package com.jumrukovski.quotescompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jumrukovski.quotescompose.ui.theme.*

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun Toolbar() {
        TopAppBar(
            modifier = Modifier.background(MaterialTheme.colorScheme.PrimaryBackgroundColor),
            title = { Text(stringResource(R.string.app_name)) },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.PrimaryBackgroundColor,
                scrolledContainerColor = MaterialTheme.colorScheme.PrimaryBackgroundColor,
                navigationIconContentColor = MaterialTheme.colorScheme.PrimaryTextColor,
                titleContentColor = MaterialTheme.colorScheme.PrimaryTextColor,
                actionIconContentColor = MaterialTheme.colorScheme.PrimaryTextColor
            ),
            actions = {
                IconButton(onClick = {
                    //todo action icon clicked
                }) {
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
    private fun BottomNavigationBar() {
        val selectedIndex = remember { mutableStateOf(0) }

        NavigationBar(
            modifier = Modifier,
            contentColor = MaterialTheme.colorScheme.NavigationBarItemRippleColor,
            tonalElevation = 0.dp,
            containerColor = MaterialTheme.colorScheme.NavigationBarBackgroundColor,
            content = {
                BottomMenuItem.values().forEachIndexed { index, menuItem ->
                    NavigationBarItem(
                        selected = selectedIndex.value == index,
                        onClick = { selectedIndex.value = index },
                        label = { Text(text = stringResource(id = menuItem.titleTextId)) },
                        enabled = true,
                        icon = {
                            Icon(
                                painter = painterResource(id = menuItem.icon),
                                contentDescription = stringResource(id = menuItem.titleTextId)
                            )
                        },
                        alwaysShowLabel = true,
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.NavigationBarSelectedItemColor,
                            unselectedIconColor = MediumDarkGreyColor,
                            selectedTextColor = MaterialTheme.colorScheme.NavigationBarSelectedItemColor,
                            unselectedTextColor = MediumDarkGreyColor,
                            indicatorColor = MaterialTheme.colorScheme.NavigationBarBackgroundColor
                        )
                    )
                }
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuotesComposeTheme {
                Scaffold(
                    topBar = { Toolbar() },
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
                    bottomBar = { BottomNavigationBar() })
            }
        }
    }
}