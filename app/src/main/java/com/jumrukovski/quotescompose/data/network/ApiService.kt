package com.jumrukovski.quotescompose.data.network

import com.jumrukovski.quotescompose.data.model.QuoteDTO
import com.jumrukovski.quotescompose.data.model.QuotesResultsDTO
import com.jumrukovski.quotescompose.data.model.TagDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("quotes")
    suspend fun getQuotes(): Response<QuotesResultsDTO>

    @GET("tags")
    suspend fun getAllTags(): Response<List<TagDTO>>

    @GET("quotes")
    suspend fun getQuotesForTag(@Query("tags") tag: String): Response<QuotesResultsDTO>

    @GET("random")
    suspend fun getRandomQuote(): Response<QuoteDTO>
}