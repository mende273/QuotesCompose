package com.jumrukovski.quotescompose.ui.common.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.jumrukovski.quotescompose.R
import com.jumrukovski.quotescompose.ui.common.state.UIState

@Composable
fun <T> UiStateWrapper(
    uiState: UIState<T>,
    onErrorRetrievingData: @Composable () -> Unit = {
        FullSizeBox { EmptyDataCard(reason = stringResource(id = R.string.error)) }
    },
    onLoading: @Composable () -> Unit = {
        FullSizeBox { ProgressBar() }
    },
    onSuccessWithData: @Composable (T) -> Unit,
    onSuccessWithoutData: @Composable () -> Unit = {
        FullSizeBox { EmptyDataCard(reason = stringResource(id = R.string.no_data)) }
    }
) {
    when (uiState) {
        UIState.Loading -> onLoading()
        UIState.ErrorRetrievingData -> onErrorRetrievingData()
        UIState.SuccessWithNoData -> onSuccessWithoutData()
        is UIState.SuccessWithData -> onSuccessWithData(uiState.data)
    }
}
