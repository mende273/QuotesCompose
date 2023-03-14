package com.jumrukovski.quotescompose.data.model

data class QuoteDTO(
    val _id: String,
    val author: String,
    val content: String,
    val tags: List<String>,
    val authorSlug: String,
    val length: Long,
    val dateAdded: String,
    val dateModified: String
)