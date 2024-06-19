package com.jumrukovski.quotescompose.data.repository

import com.jumrukovski.quotescompose.data.db.LocalDB
import com.jumrukovski.quotescompose.data.mapper.mapToFavouriteQuoteEntity
import com.jumrukovski.quotescompose.data.mapper.mapToQuote
import com.jumrukovski.quotescompose.data.mapper.mapToQuotes
import com.jumrukovski.quotescompose.domain.model.Quote
import com.jumrukovski.quotescompose.domain.repository.LocalRepository
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
