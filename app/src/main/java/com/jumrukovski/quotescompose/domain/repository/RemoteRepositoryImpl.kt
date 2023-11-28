package com.jumrukovski.quotescompose.domain.repository

import com.jumrukovski.quotescompose.data.network.ApiService
import com.jumrukovski.quotescompose.data.network.ResponseResult
import com.jumrukovski.quotescompose.domain.mapper.mapQuoteAsResponseResult
import com.jumrukovski.quotescompose.domain.mapper.mapQuotesAsResponseResult
import com.jumrukovski.quotescompose.domain.mapper.mapTagsAsResponseResult
import com.jumrukovski.quotescompose.domain.model.Quote
import com.jumrukovski.quotescompose.domain.model.Tag
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    RemoteRepository {

    override suspend fun getQuotes(): ResponseResult<List<Quote>> =
        apiService.getQuotes().mapQuotesAsResponseResult()

    override suspend fun getAllTags(): ResponseResult<List<Tag>> =
        apiService.getAllTags().mapTagsAsResponseResult()

    override suspend fun getQuotesForTag(tag: String): ResponseResult<List<Quote>> =
        apiService.getQuotesForTag(tag).mapQuotesAsResponseResult()

    override suspend fun getRandomQuote(): ResponseResult<Quote> =
        apiService.getRandomQuote().mapQuoteAsResponseResult()
}
