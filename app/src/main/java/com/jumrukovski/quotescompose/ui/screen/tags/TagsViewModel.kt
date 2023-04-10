package com.jumrukovski.quotescompose.ui.screen.tags

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumrukovski.quotescompose.data.model.dto.TagDTO
import com.jumrukovski.quotescompose.data.model.middleware.Tag
import com.jumrukovski.quotescompose.data.repository.Repository
import com.jumrukovski.quotescompose.ui.common.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class TagsViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _uiState: MutableStateFlow<UIState<List<Tag>>> =
        MutableStateFlow(UIState.Loading)
    val uiState: StateFlow<UIState<List<Tag>>> = _uiState

    suspend fun getAllTags() {
        viewModelScope.launch {
            if (_uiState.value is UIState.SuccessWithData) {
                return@launch
            }

            _uiState.value = try {
                val response: Response<List<TagDTO>> = repository.getAllTags()
                when (response.isSuccessful) {
                    true -> {
                        response.body()?.let {
                            if (it.isNotEmpty()) {
                                UIState.SuccessWithData(it.map { Tag(it._id, it.name) })
                            } else {
                                UIState.SuccessWithNoData
                            }
                        } ?: UIState.SuccessWithNoData
                    }
                    false -> {
                        UIState.Error(response.code())
                    }
                }
            } catch (e: java.lang.Exception) {
                UIState.Exception(e)
            }
        }
    }
}