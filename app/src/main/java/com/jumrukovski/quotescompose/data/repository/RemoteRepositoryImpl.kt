package com.jumrukovski.quotescompose.data.repository

import com.jumrukovski.quotescompose.data.mapper.mapQuotesAsListResult
import com.jumrukovski.quotescompose.data.mapper.mapToFirstQuoteAsResult
import com.jumrukovski.quotescompose.data.network.ApiService
import com.jumrukovski.quotescompose.domain.model.Quote
import com.jumrukovski.quotescompose.domain.repository.RemoteRepository
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    RemoteRepository {

    override suspend fun getQuotes(): Result<List<Quote>> =
        apiService.getQuotes().mapQuotesAsListResult()

    override suspend fun getRandomQuote(): Result<Quote> =
        apiService.getRandomQuote().mapToFirstQuoteAsResult()

    override suspend fun getQuoteOfTheDay(): Result<Quote> =
        apiService.getQuoteOfTheDay().mapToFirstQuoteAsResult()
}
