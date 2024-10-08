package com.jumrukovski.quotescompose.data.source.remote

import com.jumrukovski.quotescompose.data.model.dto.QuoteDTO
import retrofit2.Response
import retrofit2.http.GET

interface RemoteDataSource {

    @GET("quotes")
    suspend fun getQuotes(): Response<List<QuoteDTO>>

    @GET("random")
    suspend fun getRandomQuote(): Response<List<QuoteDTO>>

    @GET("today")
    suspend fun getQuoteOfTheDay(): Response<List<QuoteDTO>>
}
