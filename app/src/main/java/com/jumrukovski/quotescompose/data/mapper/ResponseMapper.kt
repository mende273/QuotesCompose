package com.jumrukovski.quotescompose.data.mapper

import com.jumrukovski.quotescompose.data.model.dto.QuoteDTO
import com.jumrukovski.quotescompose.data.model.dto.QuotesResultsDTO
import com.jumrukovski.quotescompose.data.model.dto.TagDTO
import com.jumrukovski.quotescompose.domain.model.Quote
import com.jumrukovski.quotescompose.domain.model.Tag
import retrofit2.Response

fun Response<List<TagDTO>>.mapTagsAsResult(): Result<List<Tag>> =
    runCatching {
        this.body()?.mapToTags() ?: emptyList()
    }

fun Response<QuotesResultsDTO>.mapQuotesAsResult(): Result<List<Quote>> =
    runCatching {
        this.body()?.results?.mapToQuotes() ?: emptyList()
    }

fun Response<QuoteDTO>.mapQuoteAsResult(): Result<Quote> =
    runCatching {
        this.body()?.mapToQuote() ?: throw Exception("Failed to retrieve data")
    }

private fun List<TagDTO>.mapToTags(): List<Tag> = this.map { Tag(id = it.id, name = it.name) }

private fun List<QuoteDTO>.mapToQuotes(): List<Quote> = this.map { it.mapToQuote() }

private fun QuoteDTO.mapToQuote(): Quote = Quote(
    id = this.id,
    content = this.content,
    author = this.author
)
