package com.jumrukovski.quotescompose.ui.screen.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumrukovski.quotescompose.data.model.TagDTO
import com.jumrukovski.quotescompose.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _items: MutableStateFlow<List<TagDTO>> = MutableStateFlow(emptyList())
    val items: StateFlow<List<TagDTO>>
        get() = _items

    suspend fun getAllTags() {
        viewModelScope.launch {
            repository.getAllTags()
            val response: Response<List<TagDTO>> = repository.getAllTags()
            if (response.isSuccessful) {
                response.body()?.let {
                    _items.value = it
                }
            }
        }
    }
}