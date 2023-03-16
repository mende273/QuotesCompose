package com.jumrukovski.quotescompose.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.jumrukovski.quotescompose.R

sealed class Screen(val route: String, @StringRes val resourceId: Int, @DrawableRes val icon: Int?) {
    object Home : Screen("home", R.string.screen_home, R.drawable.baseline_home_24)
    object Categories : Screen("categories", R.string.screen_categories, R.drawable.baseline_category_24)
    object Favourites : Screen("favourites", R.string.screen_favourites, R.drawable.baseline_favorite_24)
    object QuoteDetail: Screen("quote detail",R.string.screen_quote_detail,null)
}