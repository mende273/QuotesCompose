package com.jumrukovski.quotescompose.data.mapper

import com.jumrukovski.quotescompose.data.model.entity.QuoteEntity
import com.jumrukovski.quotescompose.domain.model.Quote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun Flow<List<QuoteEntity>>.mapToQuotes(): Flow<List<Quote>> {
    return this.map { items ->
        items.map {
            Quote(id = it.id, content = it.content, author = it.author)
        }
    }
}

fun Flow<QuoteEntity?>.mapToQuote(): Flow<Quote?> {
    return this.map {
        it?.let {
            Quote(id = it.id, content = it.content, author = it.author)
        }
    }
}

fun mapToFavouriteQuoteEntity(id: Int, content: String, author: String): QuoteEntity {
    return QuoteEntity(id, content, author)
}
