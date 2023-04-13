package com.jumrukovski.quotescompose.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavouriteQuoteEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val content: String,
    val author: String
)