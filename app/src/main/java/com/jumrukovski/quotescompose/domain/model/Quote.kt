package com.jumrukovski.quotescompose.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Quote(val id: Int, val content: String, val author: String)
