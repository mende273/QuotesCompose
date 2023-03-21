package com.jumrukovski.quotescompose.ui.common

import androidx.compose.foundation.background
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.jumrukovski.quotescompose.ui.screen.main.MainScreenMenuItem
import com.jumrukovski.quotescompose.ui.theme.PrimaryBackgroundColor
import com.jumrukovski.quotescompose.ui.theme.PrimaryTextColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(
    title: String,
    topMenuItems: List<MainScreenMenuItem> = emptyList(),
    onActionClick: (MainScreenMenuItem) -> Unit = {}) {
    TopAppBar(
        modifier = Modifier.background(MaterialTheme.colorScheme.PrimaryBackgroundColor),
        title = { Text(title) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.PrimaryBackgroundColor,
            scrolledContainerColor = MaterialTheme.colorScheme.PrimaryBackgroundColor,
            navigationIconContentColor = MaterialTheme.colorScheme.PrimaryTextColor,
            titleContentColor = MaterialTheme.colorScheme.PrimaryTextColor,
            actionIconContentColor = MaterialTheme.colorScheme.PrimaryTextColor
        ),
        actions = {
            with(topMenuItems){
                if(this.isNotEmpty()){
                    this.forEach { topMenuItem ->
                        TopMenuItem(topMenuItem = topMenuItem, onActionClick = { onActionClick(it) })
                    }
                }
            }
        }
    )
}

@Composable
private fun TopMenuItem(
    topMenuItem: MainScreenMenuItem,
    onActionClick: (MainScreenMenuItem) -> Unit = {}
) {
    IconButton(onClick = { onActionClick(topMenuItem) }) {
        Icon(
            painter = painterResource(id = topMenuItem.icon),
            contentDescription = stringResource(id = topMenuItem.titleTextId),
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}