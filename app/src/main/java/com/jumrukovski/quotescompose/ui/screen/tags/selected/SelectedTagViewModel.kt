package com.jumrukovski.quotescompose.ui.screen.tags.selected

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumrukovski.quotescompose.domain.mapper.toUiState
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
    private val remoteRepository: RemoteRepositoryImpl,
    private val savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    companion object {
        private const val TAG_KEY = "tag"
        private const val TAG_ITEMS_KEY = "tagItems"
    }

    private val _uiState: MutableStateFlow<UIState<List<Quote>>> = MutableStateFlow(UIState.Loading)
    val uiState: StateFlow<UIState<List<Quote>>> = _uiState

    suspend fun getQuotesForTag(tag: String) {
        viewModelScope.launch {
            with(savedStateHandle) {
                if (tag != this.get<String>(TAG_KEY)) {
                    this[TAG_KEY] = tag

                    this[TAG_ITEMS_KEY] = remoteRepository.getQuotesForTag(tag).fold(
                        onSuccess = { it },
                        onFailure = { null }
                    )
                }

                _uiState.value = this.get<List<Quote>>(TAG_ITEMS_KEY).toUiState()
            }
        }
    }
}
