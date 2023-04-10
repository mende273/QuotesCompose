package com.jumrukovski.quotescompose.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumrukovski.quotescompose.data.model.middleware.Quote
import com.jumrukovski.quotescompose.data.repository.Repository
import com.jumrukovski.quotescompose.ui.common.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository:Repository):ViewModel(){
    private val _uiState:MutableStateFlow<UIState<List<Quote>>> = MutableStateFlow(UIState.Loading)
    val uiState:StateFlow<UIState<List<Quote>>> = _uiState

    // todo get paginated quotes list
    suspend fun getQuotes(){
        viewModelScope.launch {
            if (_uiState.value is UIState.SuccessWithData) {
                return@launch
            }

            _uiState.value = try {
                val response = repository.getQuotes()
                when (response.isSuccessful) {
                    true -> {
                        response.body()?.let {
                            if (it.results.isNullOrEmpty()) {
                                UIState.SuccessWithNoData
                            } else {
                                UIState.SuccessWithData(it.results.map { Quote(it._id,it.content,it.author) })
                            }
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