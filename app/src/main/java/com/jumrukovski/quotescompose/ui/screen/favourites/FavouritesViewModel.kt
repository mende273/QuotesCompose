package com.jumrukovski.quotescompose.ui.screen.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumrukovski.quotescompose.data.model.entity.FavouriteQuoteEntity
import com.jumrukovski.quotescompose.data.repository.LocalRepository
import com.jumrukovski.quotescompose.ui.common.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(private val localRepository: LocalRepository) :
    ViewModel() {

    private val _uiState: MutableStateFlow<UIState<List<FavouriteQuoteEntity>>> =
        MutableStateFlow(UIState.Loading)
    val uiState: StateFlow<UIState<List<FavouriteQuoteEntity>>> = _uiState

    fun getAllFavourites() {
        viewModelScope.launch {
            localRepository.getAllFavouriteQuotesAsync().collectLatest {
                when (it.isEmpty()) {
                    true -> _uiState.value = UIState.SuccessWithNoData
                    false -> _uiState.value = UIState.SuccessWithData(it)
                }
            }
        }
    }
}