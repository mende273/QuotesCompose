package com.jumrukovski.quotescompose.ui.common

import androidx.compose.foundation.background
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.jumrukovski.quotescompose.MainActivity
import com.jumrukovski.quotescompose.ui.theme.PrimaryBackgroundColor
import com.jumrukovski.quotescompose.ui.theme.PrimaryTextColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(title:String,
    topMenuItems: List<MainActivity.MainActivityMenuItem>,
    onActionClick: (MainActivity.MainActivityMenuItem) -> Unit = {}
) {
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
            topMenuItems.forEach { topMenuItem ->
                TopMenuItem(topMenuItem = topMenuItem, onActionClick = { onActionClick(it) })
            }
        }
    )
}

@Composable
private fun TopMenuItem(
    topMenuItem: MainActivity.MainActivityMenuItem,
    onActionClick: (MainActivity.MainActivityMenuItem) -> Unit = {}
) {
    IconButton(onClick = { onActionClick(topMenuItem) }) {
        Icon(
            painter = painterResource(id = topMenuItem.drawable),
            contentDescription = topMenuItem.title,
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}