package com.jumrukovski.quotescompose.ui.common

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.jumrukovski.quotescompose.data.model.MenuItem
import com.jumrukovski.quotescompose.ui.theme.PrimaryBackgroundColor
import com.jumrukovski.quotescompose.ui.theme.PrimaryTextColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String = "",
    menuItems: List<MenuItem> = emptyList(),
    isBackButtonEnabled: Boolean = false,
    onMenuItemClick: (MenuItem) -> Unit = {},
    onNavigateBack: () -> Unit = {}
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
            with(menuItems) {
                if (this.isNotEmpty()) {
                    this.forEach { menuItem ->
                        MenuItem(menuItem = menuItem, onMenuItemClick = { onMenuItemClick(it) })
                    }
                }
            }
        },
        navigationIcon = {
            if (isBackButtonEnabled) {
                BackButton(onBackPressed = {
                    onNavigateBack()
                })
            }
        }
    )
}

@Composable
private fun BackButton(onBackPressed: () -> Unit) {
    IconButton(onClick = { onBackPressed() }) {
        Icon(Icons.Filled.ArrowBack, "backIcon")
    }
}

@Composable
private fun MenuItem(
    menuItem: MenuItem,
    onMenuItemClick: (MenuItem) -> Unit = {}
) {
    IconButton(onClick = { onMenuItemClick(menuItem) }) {
        Icon(
            painter = painterResource(id = menuItem.icon),
            contentDescription = stringResource(id = menuItem.titleTextId),
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}