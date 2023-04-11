package com.jumrukovski.quotescompose.ui.screen.tags

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumrukovski.quotescompose.data.model.middleware.Tag
import com.jumrukovski.quotescompose.data.network.ResponseResult
import com.jumrukovski.quotescompose.domain.usecase.GetAllTagsUseCase
import com.jumrukovski.quotescompose.ui.common.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TagsViewModel @Inject constructor(private val getAllTagsUseCase: GetAllTagsUseCase) :
    ViewModel() {
    private val _uiState: MutableStateFlow<UIState<List<Tag>>> = MutableStateFlow(UIState.Loading)
    val uiState: StateFlow<UIState<List<Tag>>> = _uiState

    suspend fun getAllTags() {
        viewModelScope.launch {
            if (_uiState.value is UIState.SuccessWithData) {
                return@launch
            }

            val response: ResponseResult<List<Tag>> = getAllTagsUseCase()

            _uiState.value = when (response) {
                is ResponseResult.Error -> UIState.Error(response.code)
                is ResponseResult.Exception -> UIState.Exception(response.exception)
                is ResponseResult.Success -> {
                    when (response.data.isEmpty()) {
                        true -> UIState.SuccessWithNoData
                        false -> UIState.SuccessWithData(response.data)
                    }
                }
            }
        }
    }
}