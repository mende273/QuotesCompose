package com.jumrukovski.quotescompose.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.jumrukovski.quotescompose.R

sealed class Screen(val route: String, @StringRes val resourceId: Int, @DrawableRes val icon: Int?) {
    object Home : Screen("home", R.string.screen_home, R.drawable.baseline_home_24)
    object Categories : Screen("categories", R.string.screen_categories, R.drawable.baseline_category_24)
    object Favourites : Screen("favourites", R.string.screen_favourites, R.drawable.baseline_favorite_24)
    object RandomQuote:Screen("random quote",R.string.screen_random_quote,R.drawable.baseline_random)
}

sealed class ScreenWithArgument(private val originalRoute: String, val argument:String, @StringRes resourceId: Int, @DrawableRes icon: Int?):Screen("$originalRoute{$argument}", resourceId, icon){

    fun getRouteWithArgument(argumentName:String):String{
        return originalRoute + argumentName
    }

    object QuoteDetail: ScreenWithArgument("quote detail/", "quote",R.string.screen_quote_detail,null)
    object CategoryDetail: ScreenWithArgument("category/","categoryName",R.string.screen_category_detail,null)
}