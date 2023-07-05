package com.jumrukovski.quotescompose.domain.mapper

import com.jumrukovski.quotescompose.data.model.entity.FavouriteQuoteEntity
import com.jumrukovski.quotescompose.data.model.middleware.Quote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun Flow<List<FavouriteQuoteEntity>>.mapToQuotes(): Flow<List<Quote>> {
    return this.map { items ->
        items.map {
            Quote(id = it.id, content = it.content, author = it.author)
        }
    }
}

fun Flow<FavouriteQuoteEntity?>.mapToQuote(): Flow<Quote?> {
    return this.map {
        it?.let {
            Quote(id = it.id, content = it.content, author = it.author)
        }
    }
}

fun mapToFavouriteQuoteEntity(id: String, content: String, author: String): FavouriteQuoteEntity {
    return FavouriteQuoteEntity(id, content, author)
}
