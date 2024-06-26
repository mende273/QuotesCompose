package com.jumrukovski.quotescompose.ui.feature.tags

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumrukovski.quotescompose.data.mapper.toUiState
import com.jumrukovski.quotescompose.domain.model.Tag
import com.jumrukovski.quotescompose.domain.repository.RemoteRepository
import com.jumrukovski.quotescompose.ui.common.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class TagsViewModel @Inject constructor(private val remoteRepository: RemoteRepository) :
    ViewModel() {
    private val _uiState: MutableStateFlow<UIState<List<Tag>>> = MutableStateFlow(UIState.Loading)
    val uiState: StateFlow<UIState<List<Tag>>> = _uiState

    init {
        viewModelScope.launch { getAllTags() }
    }

    private suspend fun getAllTags() {
        _uiState.value = remoteRepository.getAllTags().fold(
            onSuccess = { it },
            onFailure = { null }
        ).toUiState()
    }
}
