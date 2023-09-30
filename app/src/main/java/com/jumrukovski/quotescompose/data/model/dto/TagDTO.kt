package com.jumrukovski.quotescompose.data.model.dto

import com.squareup.moshi.Json

data class TagDTO(
    @Json(name = "_id")
    val id: String,
    val name: String,
    val slug: String,
    val quoteCount: Int,
    val dateAdded: String,
    val dateModified: String
)
