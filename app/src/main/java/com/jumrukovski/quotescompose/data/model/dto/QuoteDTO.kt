package com.jumrukovski.quotescompose.data.model.dto

import com.squareup.moshi.Json

data class QuoteDTO(
    @Json(name = "_id")
    val id: String,
    val author: String,
    val content: String,
    val tags: List<String>,
    val authorSlug: String,
    val length: Long,
    val dateAdded: String,
    val dateModified: String
)
