package com.jumrukovski.quotescompose.ui.navigation

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

        fun getNavArguments(): List<NamedNavArgument>

        @Throws(Exception::class)
        fun getRouteWithArguments(vararg values: String): String {
            if (values.size != getNavArguments().size) {
                throw Exception(
                    "Provided arguments number is greater or " +
                        "lower than the specified navArgument list"
                )
            }

            val builder = StringBuilder()
            values.forEach {
                builder.append("/$it")
            }
            return route.substringBefore("/") + builder.toString()
        }

        object SelectedTag : WithArguments {

            const val ARGUMENT_TAG_NAME = "tagName"

            override val route: String = "tag/{$ARGUMENT_TAG_NAME}"

            override fun getNavArguments(): List<NamedNavArgument> {
                return listOf(navArgument(ARGUMENT_TAG_NAME) { type = NavType.StringType })
            }
        }

        object QuoteDetail : WithArguments {

            const val ARGUMENT_ID = "id"
            const val ARGUMENT_CONTENT = "content"
            const val ARGUMENT_AUTHOR = "author"

            override val route: String =
                "quote_detail/{$ARGUMENT_ID}/{$ARGUMENT_CONTENT}/{$ARGUMENT_AUTHOR}"

            override fun getNavArguments(): List<NamedNavArgument> {
                return listOf(
                    navArgument(ARGUMENT_ID) { type = NavType.StringType },
                    navArgument(ARGUMENT_CONTENT) { type = NavType.StringType },
                    navArgument(ARGUMENT_AUTHOR) { type = NavType.StringType }
                )
            }
        }
    }
}
