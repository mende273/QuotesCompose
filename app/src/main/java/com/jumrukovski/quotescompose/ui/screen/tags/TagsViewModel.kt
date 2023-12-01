package com.jumrukovski.quotescompose.ui.screen.tags

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumrukovski.quotescompose.domain.model.Tag
import com.jumrukovski.quotescompose.domain.repository.RemoteRepositoryImpl
import com.jumrukovski.quotescompose.ui.common.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class TagsViewModel @Inject constructor(private val remoteRepository: RemoteRepositoryImpl) :
    ViewModel() {
    private val _uiState: MutableStateFlow<UIState<List<Tag>>> = MutableStateFlow(UIState.Loading)
    val uiState: StateFlow<UIState<List<Tag>>> = _uiState

    init {
        viewModelScope.launch { getAllTags() }
    }

    private suspend fun getAllTags() {
        _uiState.value = remoteRepository.getAllTags().fold(
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
