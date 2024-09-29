package com.jumrukovski.quotescompose.data.network

import com.jumrukovski.quotescompose.data.model.dto.QuoteDTO
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("quotes")
    suspend fun getQuotes(): Response<List<QuoteDTO>>

    @GET("random")
    suspend fun getRandomQuote(): Response<List<QuoteDTO>>

    @GET("today")
    suspend fun getQuoteOfTheDay(): Response<List<QuoteDTO>>
}
