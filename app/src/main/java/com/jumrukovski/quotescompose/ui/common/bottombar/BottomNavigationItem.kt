package com.jumrukovski.quotescompose.ui.common.bottombar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.jumrukovski.quotescompose.R
import com.jumrukovski.quotescompose.navigation.Screen

enum class BottomNavigationItem(
    val route: String,
    @StringRes val resourceId: Int,
    @DrawableRes val icon: Int?
) {
    HOME(Screen.WithoutArguments.Home.route, R.string.screen_home, R.drawable.baseline_home_24),
    TAGS(Screen.WithoutArguments.Tags.route, R.string.screen_tags, R.drawable.baseline_tag_24),
    FAVORITES(Screen.WithoutArguments.Favourites.route, R.string.screen_favourites, R.drawable.baseline_favorite_24)
}