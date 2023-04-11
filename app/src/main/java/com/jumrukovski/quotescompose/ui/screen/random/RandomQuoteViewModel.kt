package com.jumrukovski.quotescompose.ui.screen.random

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumrukovski.quotescompose.data.model.dto.QuoteDTO
import com.jumrukovski.quotescompose.data.repository.RemoteRepository
import com.jumrukovski.quotescompose.ui.common.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomQuoteViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository
) :
    ViewModel() {

    private val _uiState: MutableStateFlow<UIState<QuoteDTO>> = MutableStateFlow(UIState.Loading)
    val uiState: StateFlow<UIState<QuoteDTO>> = _uiState

    fun getRandomQuote() {
        viewModelScope.launch {

            _uiState.value = UIState.Loading

            _uiState.value = try {
                val response = remoteRepository.getRandomQuote()
                when (response.isSuccessful) {
                    true -> {
                        response.body()?.let {
                            UIState.SuccessWithData(it)
                        } ?: UIState.SuccessWithNoData
                    }
                    false -> UIState.Error(response.code())
                }
            } catch (e: java.lang.Exception) {
                UIState.Exception(e)
            }
        }
    }
}