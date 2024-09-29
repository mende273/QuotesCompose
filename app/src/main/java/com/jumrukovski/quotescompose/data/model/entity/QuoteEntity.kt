package com.jumrukovski.quotescompose.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuoteEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val content: String,
    val author: String
)
