package com.jumrukovski.quotescompose.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.jumrukovski.quotescompose.R

sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int,
    @DrawableRes val icon: Int?
) {
    object Home : Screen("home", R.string.screen_home, R.drawable.baseline_home_24)
    object Tags : Screen("tags", R.string.screen_tags, R.drawable.baseline_tag_24)
    object Favourites : Screen("favourites", R.string.screen_favourites, R.drawable.baseline_favorite_24)
    object RandomQuote : Screen("random quote", R.string.screen_random_quote, R.drawable.baseline_random)
}

sealed class ScreenWithArguments {

    protected abstract val route: String

    abstract fun initializeRoute(): String

    abstract fun arguments(): List<NamedNavArgument>

    fun getRouteWith(vararg arguments: String): String {
        if (arguments.size != arguments().size) {
            throw Exception("Provided arguments number is greater or lower than the specified navArgument list")
        }

        val builder = StringBuilder()
        arguments.forEach {
            builder.append("/$it")
        }
        return route + builder.toString()
    }

    protected fun initRouteArguments(): String {
        val builder = StringBuilder()
        SelectedTagScreen.arguments().map { navArgument -> navArgument.name }.forEach {
            builder.append("/{${it}}")
        }

        return builder.toString()
    }

    //  object QuoteDetail: ScreenWithArgument("quote_detail/", "{id}/{content}/{author}",R.string.screen_quote_detail,null)
    object SelectedTagScreen : ScreenWithArguments() {

        const val ARGUMENT_TAG_NAME = "tagName"

        override val route: String = "tag"

        override fun initializeRoute(): String {
            return "$route${initRouteArguments()}"
        }

        override fun arguments(): List<NamedNavArgument> {
            return listOf(navArgument(ARGUMENT_TAG_NAME) { type = NavType.StringType })
        }
    }
}