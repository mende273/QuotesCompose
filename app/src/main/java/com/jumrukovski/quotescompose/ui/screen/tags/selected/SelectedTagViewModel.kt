package com.jumrukovski.quotescompose.ui.screen.tags.selected

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumrukovski.quotescompose.data.model.dto.QuotesResultsDTO
import com.jumrukovski.quotescompose.data.model.middleware.Quote
import com.jumrukovski.quotescompose.data.repository.Repository
import com.jumrukovski.quotescompose.ui.common.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SelectedTagViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _uiState: MutableStateFlow<UIState<List<Quote>>> =
        MutableStateFlow(UIState.Loading)
    val uiState: StateFlow<UIState<List<Quote>>> = _uiState

    private var currentTag = ""

    suspend fun getQuotesForTag(tag: String) {
        viewModelScope.launch {
            if (tag == currentTag) {
                return@launch
            }

            currentTag = tag

            if (_uiState.value !is UIState.Loading) {
                _uiState.value = UIState.Loading
            }

            _uiState.value = try {
                val response: Response<QuotesResultsDTO> = repository.getQuotesForTag(tag)
                when (response.isSuccessful) {
                    true -> {
                        response.body()?.let {
                            if (it.results.isNullOrEmpty()) {
                                UIState.SuccessWithNoData
                            } else {
                                UIState.SuccessWithData(it.results.map {
                                    Quote(
                                        it._id,
                                        it.content,
                                        it.author
                                    )
                                })
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