package com.jumrukovski.quotescompose.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumrukovski.quotescompose.data.network.ResponseResult
import com.jumrukovski.quotescompose.domain.model.Quote
import com.jumrukovski.quotescompose.domain.repository.RemoteRepositoryImpl
import com.jumrukovski.quotescompose.ui.common.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(private val remoteRepository: RemoteRepositoryImpl) :
    ViewModel() {
    private val _uiState: MutableStateFlow<UIState<List<Quote>>> = MutableStateFlow(UIState.Loading)
    val uiState: StateFlow<UIState<List<Quote>>> = _uiState

    // todo get paginated quotes list
    suspend fun getQuotes() {
        viewModelScope.launch {
            if (_uiState.value is UIState.SuccessWithData) {
                return@launch
            }

            with(remoteRepository.getQuotes()) {
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
