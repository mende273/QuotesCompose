package com.jumrukovski.quotescompose.domain.mapper

import com.jumrukovski.quotescompose.data.model.dto.TagDTO
import com.jumrukovski.quotescompose.data.model.middleware.Tag
import com.jumrukovski.quotescompose.data.network.ResponseResult
import retrofit2.Response

fun Response<List<TagDTO>>.wrapAsResponseResult(): ResponseResult<List<Tag>> {
    return try {
        return when (this.isSuccessful) {
            true -> ResponseResult.Success(this.body()?.mapToTags() ?: emptyList())
            false -> ResponseResult.Error(this.errorBody(), this.code())
        }
    } catch (throwable: Throwable) {
        ResponseResult.Exception(throwable)
    }
}

fun List<TagDTO>.mapToTags(): List<Tag> = this.map { Tag(it._id, it.name) }