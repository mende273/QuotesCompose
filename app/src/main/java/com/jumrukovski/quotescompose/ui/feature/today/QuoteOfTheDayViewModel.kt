package com.jumrukovski.quotescompose.ui.feature.today

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumrukovski.quotescompose.domain.model.Quote
import com.jumrukovski.quotescompose.domain.repository.remote.RemoteRepository
import com.jumrukovski.quotescompose.ui.common.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class QuoteOfTheDayViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<UIState<Quote>> = MutableStateFlow(UIState.Loading)
    val uiState: StateFlow<UIState<Quote>> = _uiState

    fun getRandomQuote() {
        viewModelScope.launch {
            _uiState.value = UIState.Loading

            _uiState.value = remoteRepository.getQuoteOfTheDay().fold(
                onSuccess = {
                    UIState.SuccessWithData(it)
                },
                onFailure = {
                    UIState.ErrorRetrievingData
                }
            )
        }
    }
}
