package com.jumrukovski.quotescompose.data.mapper

import com.jumrukovski.quotescompose.ui.common.state.UIState

fun <T> List<T>?.toUiState(): UIState<List<T>> {
    return when (this) {
        null -> UIState.ErrorRetrievingData
        else -> {
            if (this.isEmpty()) {
                UIState.SuccessWithNoData
            } else {
                UIState.SuccessWithData(this)
            }
        }
    }
}
