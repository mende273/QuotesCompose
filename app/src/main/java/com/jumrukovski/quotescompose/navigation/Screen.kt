package com.jumrukovski.quotescompose.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed interface Screen {
    val route: String

    sealed interface WithoutArguments : Screen {

        object Home : WithoutArguments {
            override val route: String = "home"
        }

        object Tags : WithoutArguments {
            override val route: String = "tags"
        }

        object Favourites : WithoutArguments {
            override val route: String = "favourites"
        }

        object RandomQuote : WithoutArguments {
            override val route: String = "random quote"
        }
    }

    sealed interface WithArguments : Screen {
        val routeWithArguments: String
            get() = "${route}${prepareArguments()}"

        fun arguments(): List<NamedNavArgument>

        private fun prepareArguments(): String {
            val args = arguments()
            if (args.isEmpty()) {
                return ""
            }

            val builder = StringBuilder()
            args.map { navArgument -> navArgument.name }.forEach {
                builder.append("/{${it}}")
            }

            return builder.toString()
        }

        @Throws(Exception::class)
        fun getRouteWith(vararg values: String): String {
            if (values.size != arguments().size) {
                throw Exception("Provided arguments number is greater or lower than the specified navArgument list")
            }

            val builder = StringBuilder()
            values.forEach {
                builder.append("/$it")
            }
            return route + builder.toString()
        }

        object SelectedTag : WithArguments {

            const val ARGUMENT_TAG_NAME = "tagName"

            override val route: String = "tag"

            override fun arguments(): List<NamedNavArgument> {
                return listOf(navArgument(ARGUMENT_TAG_NAME) { type = NavType.StringType })
            }
        }

        object QuoteDetail : WithArguments {

            const val ARGUMENT_ID = "id"
            const val ARGUMENT_CONTENT = "content"
            const val ARGUMENT_AUTHOR = "author"

            override val route: String = "quote_detail"

            override fun arguments(): List<NamedNavArgument> {
                return listOf(
                    navArgument(ARGUMENT_ID) { type = NavType.StringType },
                    navArgument(ARGUMENT_CONTENT) { type = NavType.StringType },
                    navArgument(ARGUMENT_AUTHOR) { type = NavType.StringType }
                )
            }
        }
    }
}