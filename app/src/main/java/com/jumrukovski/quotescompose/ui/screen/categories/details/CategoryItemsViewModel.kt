package com.jumrukovski.quotescompose.ui.screen.categories.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumrukovski.quotescompose.data.model.QuoteDTO
import com.jumrukovski.quotescompose.data.model.QuotesResultsDTO
import com.jumrukovski.quotescompose.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CategoryItemsViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _items: MutableStateFlow<List<QuoteDTO>> = MutableStateFlow(emptyList())
    val items: StateFlow<List<QuoteDTO>> = _items

    suspend fun getQuotesForTag(tag: String) {
        viewModelScope.launch {
            val response: Response<QuotesResultsDTO> = repository.getQuotesForTag(tag)
            if (response.isSuccessful) {
                response.body()?.let {
                    _items.value = it.results
                } ?: emptyList<List<QuoteDTO>>()
            }
        }
    }
}