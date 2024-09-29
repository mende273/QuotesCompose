package com.jumrukovski.quotescompose.domain.repository

import com.jumrukovski.quotescompose.domain.model.Quote
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    fun getAllFavouriteQuotes(): Flow<List<Quote>>
    fun getFavouriteQuote(id: Int): Flow<Quote?>
    fun addFavouriteQuote(id: Int, content: String, author: String)
    fun removeFavouriteQuote(id: Int, content: String, author: String)
}
