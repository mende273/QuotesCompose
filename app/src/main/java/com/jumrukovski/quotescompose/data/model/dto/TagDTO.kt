package com.jumrukovski.quotescompose.data.model.dto

data class TagDTO(
    val _id: String,
    val name: String,
    val slug: String,
    val quoteCount: Int,
    val dateAdded: String,
    val dateModified: String
)
