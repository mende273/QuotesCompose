package com.jumrukovski.quotescompose.ui.feature.tag

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumrukovski.quotescompose.domain.model.Quote
import com.jumrukovski.quotescompose.domain.repository.RemoteRepository
import com.jumrukovski.quotescompose.ui.common.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SelectedTagViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    companion object {
        private const val TAG_KEY = "tag"
    }

    private val _uiState: MutableStateFlow<UIState<List<Quote>>> = MutableStateFlow(UIState.Loading)
    val uiState: StateFlow<UIState<List<Quote>>> = _uiState

    suspend fun getQuotesForTag(tag: String) {
        viewModelScope.launch {
            with(savedStateHandle) {
                if (tag != this.get<String>(TAG_KEY)) {
                    this[TAG_KEY] = tag

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
    }
}
