package com.jumrukovski.quotescompose.data.repository

import com.jumrukovski.quotescompose.data.model.dto.QuoteDTO
import com.jumrukovski.quotescompose.data.model.dto.QuotesResultsDTO
import com.jumrukovski.quotescompose.data.model.dto.TagDTO
import com.jumrukovski.quotescompose.data.network.ApiService
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService) {

    suspend fun getQuotes():Response<QuotesResultsDTO>{
        return apiService.getQuotes()
    }

    suspend fun getAllTags(): Response<List<TagDTO>> {
        return apiService.getAllTags()
    }

    suspend fun getQuotesForTag(tag: String): Response<QuotesResultsDTO> {
        return apiService.getQuotesForTag(tag)
    }

    suspend fun getRandomQuote(): Response<QuoteDTO> {
        return apiService.getRandomQuote()
    }
}