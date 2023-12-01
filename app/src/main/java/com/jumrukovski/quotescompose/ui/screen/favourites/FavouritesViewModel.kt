package com.jumrukovski.quotescompose.ui.screen.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumrukovski.quotescompose.domain.model.Quote
import com.jumrukovski.quotescompose.domain.repository.LocalRepositoryImpl
import com.jumrukovski.quotescompose.ui.common.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val localRepository: LocalRepositoryImpl
) : ViewModel() {

    private val _uiState: MutableStateFlow<UIState<List<Quote>>> =
        MutableStateFlow(UIState.Loading)
    val uiState: StateFlow<UIState<List<Quote>>> = _uiState

    init {
        viewModelScope.launch { getAllFavourites() }
    }

    private suspend fun getAllFavourites() {
        localRepository.getAllFavouriteQuotesAsync().collectLatest {
            _uiState.value = when (it.isEmpty()) {
                true -> UIState.SuccessWithNoData
                false -> UIState.SuccessWithData(it)
            }
        }
    }
}
