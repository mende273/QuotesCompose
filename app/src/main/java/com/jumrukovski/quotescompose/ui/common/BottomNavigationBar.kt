package com.jumrukovski.quotescompose.ui.common

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jumrukovski.quotescompose.BottomMenuItem
import com.jumrukovski.quotescompose.ui.theme.MediumDarkGreyColor
import com.jumrukovski.quotescompose.ui.theme.NavigationBarBackgroundColor
import com.jumrukovski.quotescompose.ui.theme.NavigationBarItemRippleColor
import com.jumrukovski.quotescompose.ui.theme.NavigationBarSelectedItemColor

@Composable
fun BottomNavigationBar(onItemClick: (BottomMenuItem) -> Unit = {}) {
    val selectedIndex = rememberSaveable{ mutableStateOf(0) }

    NavigationBar(
        modifier = Modifier,
        contentColor = MaterialTheme.colorScheme.NavigationBarItemRippleColor,
        tonalElevation = 0.dp,
        containerColor = MaterialTheme.colorScheme.NavigationBarBackgroundColor,
        content = {
            BottomMenuItem.values().forEachIndexed { index, menuItem ->
                NavigationBarItem(
                    selected = selectedIndex.value == index,
                    onClick = {
                        selectedIndex.value = index
                        onItemClick(BottomMenuItem.values()[index])
                    },
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