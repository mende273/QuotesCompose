package com.jumrukovski.quotescompose.data.network

import okhttp3.ResponseBody

sealed class ResponseResult<out R> {
    data class Success<T>(val data: T) : ResponseResult<T>()
    data class Error(val errorBody: ResponseBody?, val code: Int) : ResponseResult<Nothing>()
    data class Exception(val exception: Throwable?) : ResponseResult<Nothing>()
}