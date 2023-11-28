package com.jumrukovski.quotescompose.domain.repository

import com.jumrukovski.quotescompose.data.db.LocalDB
import com.jumrukovski.quotescompose.domain.mapper.mapToFavouriteQuoteEntity
import com.jumrukovski.quotescompose.domain.mapper.mapToQuote
import com.jumrukovski.quotescompose.domain.mapper.mapToQuotes
import com.jumrukovski.quotescompose.domain.model.Quote
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class LocalRepositoryImpl @Inject constructor(private val localDB: LocalDB) : LocalRepository {

    override fun getAllFavouriteQuotesAsync(): Flow<List<Quote>> =
        localDB.quoteDao().getAllFavouriteQuotesAsync().mapToQuotes()

    override fun getFavouriteQuoteAsync(id: String): Flow<Quote?> =
        localDB.quoteDao().getFavouriteQuoteAsync(id).mapToQuote()

    override fun addFavouriteQuote(id: String, content: String, author: String) {
        val entity = mapToFavouriteQuoteEntity(id, content, author)
        localDB.quoteDao().addFavouriteQuote(entity)
    }

    override fun removeFavouriteQuote(id: String, content: String, author: String) {
        val entity = mapToFavouriteQuoteEntity(id, content, author)
        localDB.quoteDao().deleteFavouriteQuote(entity)
    }
}
