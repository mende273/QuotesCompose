package com.jumrukovski.quotescompose.domain.repository

import com.jumrukovski.quotescompose.data.network.ResponseResult
import com.jumrukovski.quotescompose.domain.model.Quote
import com.jumrukovski.quotescompose.domain.model.Tag

interface RemoteRepository {
    suspend fun getQuotes(): ResponseResult<List<Quote>>
    suspend fun getAllTags(): ResponseResult<List<Tag>>
    suspend fun getQuotesForTag(tag: String): ResponseResult<List<Quote>>
    suspend fun getRandomQuote(): ResponseResult<Quote>
}
