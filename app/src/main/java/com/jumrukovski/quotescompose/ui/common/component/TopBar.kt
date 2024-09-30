package com.jumrukovski.quotescompose.ui.common.component

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jumrukovski.quotescompose.R
import com.jumrukovski.quotescompose.ui.menu.MenuItem
import com.jumrukovski.quotescompose.ui.theme.PrimaryBackgroundColor
import com.jumrukovski.quotescompose.ui.theme.PrimaryTextColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String = "",
    menuItem: MenuItem? = null,
    isBackButtonEnabled: Boolean = false,
    onMenuItemClick: () -> Unit = {},
    onNavigateBack: () -> Unit = {}
) {
    TopAppBar(
        modifier = Modifier.background(PrimaryBackgroundColor),
        title = { Text(title) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = PrimaryBackgroundColor,
            scrolledContainerColor = PrimaryBackgroundColor,
            navigationIconContentColor = PrimaryTextColor,
            titleContentColor = PrimaryTextColor,
            actionIconContentColor = PrimaryTextColor
        ),
        actions = {
            menuItem?.let {
                MenuItem(menuItem = menuItem, onMenuItemClick = onMenuItemClick)
            }
        },
        navigationIcon = {
            if (isBackButtonEnabled) {
                BackButton(onBackPressed = onNavigateBack)
            }
        }
    )
}

@Preview
@Composable
private fun TopBarWithMenuItemPreview() {
    TopBar(
        title = "Home",
        menuItem = MenuItem(
            R.string.action_random,
            R.drawable.baseline_random
        )
    )
}

@Preview
@Composable
private fun TopBarWithBackButtonAndMenuItemPreview() {
    TopBar(
        title = "Home",
        menuItem = MenuItem(
            R.string.action_random,
            R.drawable.baseline_random
        ),
        isBackButtonEnabled = true
    )
}

@Preview
@Composable
private fun TopBarWithBackButtonPreview() {
    TopBar(
        title = "Home",
        isBackButtonEnabled = true
    )
}

@Composable
private fun BackButton(onBackPressed: () -> Unit) {
    IconButton(onClick = onBackPressed) {
        Icon(Icons.AutoMirrored.Filled.ArrowBack, "backIcon")
    }
}

@Preview
@Composable
private fun BackButtonPreview() {
    BackButton(onBackPressed = {})
}

@Composable
private fun MenuItem(
    menuItem: MenuItem,
    onMenuItemClick: () -> Unit = {}
) {
    IconButton(onClick = onMenuItemClick) {
        Icon(
            painter = painterResource(id = menuItem.icon),
            contentDescription = stringResource(id = menuItem.titleTextId),
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview
@Composable
private fun MenuItemPreview() {
    MenuItem(
        menuItem = MenuItem(
            R.string.action_favourite,
            R.drawable.baseline_favorite_border_24
        )
    )
}
