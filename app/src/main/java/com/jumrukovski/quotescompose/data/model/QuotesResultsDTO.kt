package com.jumrukovski.quotescompose.data.model

data class QuotesResultsDTO(
    val count: Int,
    val totalCount: Int,
    val page: Int,
    val totalPages: Int,
    val results: List<QuoteDTO>
)