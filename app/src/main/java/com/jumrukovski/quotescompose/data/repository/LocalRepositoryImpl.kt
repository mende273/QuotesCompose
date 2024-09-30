package com.jumrukovski.quotescompose.data.repository

import com.jumrukovski.quotescompose.data.db.LocalDB
import com.jumrukovski.quotescompose.data.mapper.mapToQuote
import com.jumrukovski.quotescompose.data.mapper.mapToQuoteEntity
import com.jumrukovski.quotescompose.data.mapper.mapToQuotes
import com.jumrukovski.quotescompose.di.Dispatcher
import com.jumrukovski.quotescompose.di.QuotesDispatchers
import com.jumrukovski.quotescompose.domain.model.Quote
import com.jumrukovski.quotescompose.domain.repository.LocalRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class LocalRepositoryImpl @Inject constructor(
    private val localDB: LocalDB,
    @Dispatcher(QuotesDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : LocalRepository {

    override suspend fun getAllFavouriteQuotes(): Flow<List<Quote>> =
        withContext(ioDispatcher) {
            localDB.quoteDao().getAllFavouriteQuotes().mapToQuotes()
        }

    override suspend fun getFavouriteQuote(id: Int): Flow<Quote?> =
        withContext(ioDispatcher) {
            localDB.quoteDao().getFavouriteQuote(id).mapToQuote()
        }

    override suspend fun addFavouriteQuote(quote: Quote) {
        withContext(ioDispatcher) {
            val entity = quote.mapToQuoteEntity()
            localDB.quoteDao().addFavouriteQuote(entity)
        }
    }

    override suspend fun removeFavouriteQuote(quote: Quote) {
        withContext(ioDispatcher) {
            val entity = quote.mapToQuoteEntity()
            localDB.quoteDao().deleteFavouriteQuote(entity)
        }
    }
}
