package com.jumrukovski.quotescompose.ui.screen.tags.selected

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumrukovski.quotescompose.domain.model.Quote
import com.jumrukovski.quotescompose.domain.repository.RemoteRepositoryImpl
import com.jumrukovski.quotescompose.ui.common.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SelectedTagViewModel @Inject constructor(
    private val remoteRepository: RemoteRepositoryImpl
) :
    ViewModel() {

    private val _uiState: MutableStateFlow<UIState<List<Quote>>> = MutableStateFlow(UIState.Loading)
    val uiState: StateFlow<UIState<List<Quote>>> = _uiState

    private var currentTag = ""

    suspend fun getQuotesForTag(tag: String) {
        viewModelScope.launch {
            if (tag == currentTag) {
                return@launch
            }

            currentTag = tag

            _uiState.value = remoteRepository.getQuotesForTag(tag).fold(
                onSuccess = {
                    when (it.isEmpty()) {
                        true -> UIState.SuccessWithNoData
                        false -> UIState.SuccessWithData(it)
                    }
                },
                onFailure = {
                    UIState.ErrorRetrievingData
                }
            )
        }
    }
}
