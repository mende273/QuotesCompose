package com.jumrukovski.quotescompose.domain.mapper

import com.jumrukovski.quotescompose.domain.model.Quote
import com.jumrukovski.quotescompose.ui.common.state.UIState

fun List<Quote>?.toUiState(): UIState<List<Quote>> {
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
