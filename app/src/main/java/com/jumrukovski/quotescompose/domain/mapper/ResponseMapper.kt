package com.jumrukovski.quotescompose.domain.mapper

import com.jumrukovski.quotescompose.data.model.dto.QuoteDTO
import com.jumrukovski.quotescompose.data.model.dto.QuotesResultsDTO
import com.jumrukovski.quotescompose.data.model.dto.TagDTO
import com.jumrukovski.quotescompose.data.network.ResponseResult
import com.jumrukovski.quotescompose.domain.model.Quote
import com.jumrukovski.quotescompose.domain.model.Tag
import retrofit2.Response

fun Response<List<TagDTO>>.mapTagsAsResponseResult(): ResponseResult<List<Tag>> {
    return try {
        return when (this.isSuccessful) {
            true -> ResponseResult.Success(this.body()?.mapToTags() ?: emptyList())
            false -> ResponseResult.Error(this.errorBody(), this.code())
        }
    } catch (throwable: Throwable) {
        ResponseResult.Exception(throwable)
    }
}

fun Response<QuotesResultsDTO>.mapQuotesAsResponseResult(): ResponseResult<List<Quote>> {
    return try {
        return when (this.isSuccessful) {
            true -> ResponseResult.Success(this.body()?.results?.mapToQuotes() ?: emptyList())
            false -> ResponseResult.Error(this.errorBody(), this.code())
        }
    } catch (throwable: Throwable) {
        ResponseResult.Exception(throwable)
    }
}

fun Response<QuoteDTO>.mapQuoteAsResponseResult(): ResponseResult<Quote> {
    return try {
        return when (this.isSuccessful) {
            true -> {
                this.body()?.let {
                    ResponseResult.Success(it.mapToQuote())
                } ?: ResponseResult.Exception(Exception("Quote is null"))
            }

            false -> ResponseResult.Error(this.errorBody(), this.code())
        }
    } catch (throwable: Throwable) {
        ResponseResult.Exception(throwable)
    }
}

private fun List<TagDTO>.mapToTags(): List<Tag> = this.map { Tag(id = it.id, name = it.name) }

private fun List<QuoteDTO>.mapToQuotes(): List<Quote> = this.map { it.mapToQuote() }

private fun QuoteDTO.mapToQuote(): Quote = Quote(
    id = this.id,
    content = this.content,
    author = this.author
)
