package com.jumrukovski.quotescompose.data.repository

import com.jumrukovski.quotescompose.data.mapper.mapToQuotesList
import com.jumrukovski.quotescompose.data.network.ApiService
import com.jumrukovski.quotescompose.di.Dispatcher
import com.jumrukovski.quotescompose.di.QuotesDispatchers
import com.jumrukovski.quotescompose.domain.model.Quote
import com.jumrukovski.quotescompose.domain.repository.RemoteRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class RemoteRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    @Dispatcher(QuotesDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) :
    RemoteRepository {

    override suspend fun getQuotes(): Result<List<Quote>> =
        withContext(ioDispatcher) {
            runCatching {
                apiService.getQuotes().mapToQuotesList()
            }
        }

    override suspend fun getRandomQuote(): Result<Quote> =
        withContext(ioDispatcher) {
            runCatching {
                apiService.getRandomQuote().mapToQuotesList().first()
            }
        }

    override suspend fun getQuoteOfTheDay(): Result<Quote> =
        withContext(ioDispatcher) {
            runCatching {
                apiService.getQuoteOfTheDay().mapToQuotesList().first()
            }
        }
}
