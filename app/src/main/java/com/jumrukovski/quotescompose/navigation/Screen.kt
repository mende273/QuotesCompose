package com.jumrukovski.quotescompose.navigation

import com.jumrukovski.quotescompose.domain.model.Quote
import kotlinx.serialization.Serializable

sealed interface Screen {

    @Serializable
    data object Home : Screen

    @Serializable
    data object Favourites : Screen

    @Serializable
    data object RandomQuote : Screen

    @Serializable
    data object QuoteOfTheDay : Screen

    @Serializable
    data class QuoteDetail(val quote: Quote) : Screen
}
