package com.jumrukovski.quotescompose.ui.common.state

sealed interface UIState<out T> {
    data class SuccessWithData<T>(val data: T) : UIState<T>
    data class Error(val code: Int) : UIState<Nothing>
    data class Exception(val exception: Throwable?) : UIState<Nothing>
    object SuccessWithNoData : UIState<Nothing>
    object Loading : UIState<Nothing>
}
