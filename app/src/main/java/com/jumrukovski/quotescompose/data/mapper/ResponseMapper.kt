package com.jumrukovski.quotescompose.data.mapper

import com.jumrukovski.quotescompose.data.model.dto.QuoteDTO
import com.jumrukovski.quotescompose.domain.model.Quote
import retrofit2.Response

fun Response<List<QuoteDTO>>.mapToQuotesList(): List<Quote> =
    this.body()?.mapToQuotes() ?: emptyList()

private fun List<QuoteDTO>.mapToQuotes(): List<Quote> = this.map { it.mapToQuote() }

private fun QuoteDTO.mapToQuote(): Quote = Quote(
    id = this.c ?: 0,
    content = this.q,
    author = this.a
)
