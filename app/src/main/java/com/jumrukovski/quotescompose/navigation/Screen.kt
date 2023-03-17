package com.jumrukovski.quotescompose.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.jumrukovski.quotescompose.R

sealed class Screen(open val route: String, @StringRes val resourceId: Int, @DrawableRes val icon: Int?) {

    object Home : Screen("home", R.string.screen_home, R.drawable.baseline_home_24)
    object Categories : Screen("categories", R.string.screen_categories, R.drawable.baseline_category_24)
    object Favourites : Screen("favourites", R.string.screen_favourites, R.drawable.baseline_favorite_24)
    object QuoteDetail: Screen("quote detail/",R.string.screen_quote_detail,null){
        const val QUOTE_ARGUMENT = "quote"

        override val route: String get() = "quote detail/{${QUOTE_ARGUMENT}}"
    }
    object CategoryDetail: Screen("category/",R.string.screen_category_detail,null){
        const val CATEGORY_NAME_ARGUMENT = "categoryName"

        override val route: String get() = "category/{$CATEGORY_NAME_ARGUMENT}"

        fun getRouteWithArgument(argument:String):String = "category/$argument"
    }
}