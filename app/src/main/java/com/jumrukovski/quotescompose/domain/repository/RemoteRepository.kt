package com.jumrukovski.quotescompose.domain.repository

import com.jumrukovski.quotescompose.domain.model.Quote
import com.jumrukovski.quotescompose.domain.model.Tag

interface RemoteRepository {
    suspend fun getQuotes(): Result<List<Quote>>
    suspend fun getAllTags(): Result<List<Tag>>
    suspend fun getQuotesForTag(tag: String): Result<List<Quote>>
    suspend fun getRandomQuote(): Result<Quote>
}
