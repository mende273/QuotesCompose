package com.jumrukovski.quotescompose.domain.repository

import com.jumrukovski.quotescompose.data.network.ApiService
import com.jumrukovski.quotescompose.domain.mapper.mapQuoteAsResult
import com.jumrukovski.quotescompose.domain.mapper.mapQuotesAsResult
import com.jumrukovski.quotescompose.domain.mapper.mapTagsAsResult
import com.jumrukovski.quotescompose.domain.model.Quote
import com.jumrukovski.quotescompose.domain.model.Tag
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    RemoteRepository {

    override suspend fun getQuotes(): Result<List<Quote>> =
        apiService.getQuotes().mapQuotesAsResult()

    override suspend fun getAllTags(): Result<List<Tag>> =
        apiService.getAllTags().mapTagsAsResult()

    override suspend fun getQuotesForTag(tag: String): Result<List<Quote>> =
        apiService.getQuotesForTag(tag).mapQuotesAsResult()

    override suspend fun getRandomQuote(): Result<Quote> =
        apiService.getRandomQuote().mapQuoteAsResult()
}
