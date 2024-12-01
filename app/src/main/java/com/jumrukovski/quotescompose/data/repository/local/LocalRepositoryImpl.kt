package com.jumrukovski.quotescompose.data.repository.local

import com.jumrukovski.quotescompose.data.mapper.mapToQuote
import com.jumrukovski.quotescompose.data.mapper.mapToQuoteEntity
import com.jumrukovski.quotescompose.data.mapper.mapToQuotes
import com.jumrukovski.quotescompose.data.source.local.LocalDataSource
import com.jumrukovski.quotescompose.di.Dispatcher
import com.jumrukovski.quotescompose.di.QuotesDispatchers
import com.jumrukovski.quotescompose.domain.model.Quote
import com.jumrukovski.quotescompose.domain.repository.local.LocalRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class LocalRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    @Dispatcher(QuotesDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : LocalRepository {

    override fun getAllFavouriteQuotes(): Flow<List<Quote>> =
        localDataSource.quoteDao().getAllFavouriteQuotes().mapToQuotes()

    override fun getFavouriteQuote(id: Int): Flow<Quote?> =
        localDataSource.quoteDao().getFavouriteQuote(id).mapToQuote()

    override suspend fun addFavouriteQuote(quote: Quote) {
        withContext(ioDispatcher) {
            val entity = quote.mapToQuoteEntity()
            localDataSource.quoteDao().addFavouriteQuote(entity)
        }
    }

    override suspend fun removeFavouriteQuote(quote: Quote) {
        withContext(ioDispatcher) {
            val entity = quote.mapToQuoteEntity()
            localDataSource.quoteDao().deleteFavouriteQuote(entity)
        }
    }
}
