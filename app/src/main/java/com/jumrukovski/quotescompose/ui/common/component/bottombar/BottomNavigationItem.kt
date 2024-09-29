package com.jumrukovski.quotescompose.ui.common.component.bottombar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.jumrukovski.quotescompose.R
import com.jumrukovski.quotescompose.ui.navigation.Screen

enum class BottomNavigationItem(
    val route: String,
    @StringRes val resourceId: Int,
    @DrawableRes val icon: Int?
) {
    HOME(Screen.WithoutArguments.Home.route, R.string.screen_home, R.drawable.baseline_home_24),
    QUOTE_OF_THE_DAY(
        Screen.WithoutArguments.QuoteOfTheDay.route,
        R.string.screen_quote_today,
        R.drawable.baseline_today_24
    ),
    FAVORITES(
        Screen.WithoutArguments.Favourites.route,
        R.string.screen_favourites,
        R.drawable.baseline_favorite_24
    )
}
