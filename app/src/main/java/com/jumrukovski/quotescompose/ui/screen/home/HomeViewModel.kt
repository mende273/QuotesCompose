package com.jumrukovski.quotescompose.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumrukovski.quotescompose.data.model.middleware.Quote
import com.jumrukovski.quotescompose.data.network.ResponseResult
import com.jumrukovski.quotescompose.domain.usecase.GetQuotesUseCase
import com.jumrukovski.quotescompose.ui.common.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getQuotesUseCase: GetQuotesUseCase) :
    ViewModel() {
    private val _uiState: MutableStateFlow<UIState<List<Quote>>> = MutableStateFlow(UIState.Loading)
    val uiState: StateFlow<UIState<List<Quote>>> = _uiState

    // todo get paginated quotes list
    suspend fun getQuotes() {
        viewModelScope.launch {
            if (_uiState.value is UIState.SuccessWithData) {
                return@launch
            }

            with(getQuotesUseCase()) {
                _uiState.value = when (this) {
                    is ResponseResult.Error -> UIState.Error(code)
                    is ResponseResult.Exception -> UIState.Exception(exception)
                    is ResponseResult.Success -> when (data.isEmpty()) {
                        true -> UIState.SuccessWithNoData
                        false -> UIState.SuccessWithData(data)
                    }
                }
            }
        }
    }
}