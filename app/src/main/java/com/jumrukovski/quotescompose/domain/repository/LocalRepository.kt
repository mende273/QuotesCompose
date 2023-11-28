package com.jumrukovski.quotescompose.domain.repository

import com.jumrukovski.quotescompose.domain.model.Quote
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    fun getAllFavouriteQuotesAsync(): Flow<List<Quote>>
    fun getFavouriteQuoteAsync(id: String): Flow<Quote?>
    fun addFavouriteQuote(id: String, content: String, author: String)
    fun removeFavouriteQuote(id: String, content: String, author: String)
}
