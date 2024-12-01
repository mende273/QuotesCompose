package com.jumrukovski.quotescompose.domain.repository.local

import com.jumrukovski.quotescompose.domain.model.Quote
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    fun getAllFavouriteQuotes(): Flow<List<Quote>>
    fun getFavouriteQuote(id: Int): Flow<Quote?>
    suspend fun addFavouriteQuote(quote: Quote)
    suspend fun removeFavouriteQuote(quote: Quote)
}
