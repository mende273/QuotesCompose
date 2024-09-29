package com.jumrukovski.quotescompose.domain.repository

import com.jumrukovski.quotescompose.domain.model.Quote

interface RemoteRepository {
    suspend fun getQuotes(): Result<List<Quote>>
    suspend fun getRandomQuote(): Result<Quote>
    suspend fun getQuoteOfTheDay(): Result<Quote>
}
