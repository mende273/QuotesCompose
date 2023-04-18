package com.jumrukovski.quotescompose.ui.screen.random

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumrukovski.quotescompose.data.model.middleware.Quote
import com.jumrukovski.quotescompose.data.network.ResponseResult
import com.jumrukovski.quotescompose.domain.usecase.GetRandomQuoteUseCase
import com.jumrukovski.quotescompose.ui.common.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomQuoteViewModel @Inject constructor(
    private val getRandomQuoteUseCase: GetRandomQuoteUseCase
) :
    ViewModel() {

    private val _uiState: MutableStateFlow<UIState<Quote>> = MutableStateFlow(UIState.Loading)
    val uiState: StateFlow<UIState<Quote>> = _uiState

    fun getRandomQuote() {
        viewModelScope.launch {

            _uiState.value = UIState.Loading

            with(getRandomQuoteUseCase()) {
                _uiState.value = when (this) {
                    is ResponseResult.Error -> UIState.Error(code)
                    is ResponseResult.Exception -> UIState.Exception(exception)
                    is ResponseResult.Success -> when (data == null) {
                        true -> UIState.SuccessWithNoData
                        false -> UIState.SuccessWithData(data)
                    }
                }
            }
        }
    }
}