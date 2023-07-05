package com.jumrukovski.quotescompose.ui.screen.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumrukovski.quotescompose.data.model.middleware.Quote
import com.jumrukovski.quotescompose.domain.usecase.GetAllFavouriteQuotesUseCase
import com.jumrukovski.quotescompose.ui.common.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val getAllFavouriteQuotesUseCase: GetAllFavouriteQuotesUseCase
) :
    ViewModel() {

    private val _uiState: MutableStateFlow<UIState<List<Quote>>> =
        MutableStateFlow(UIState.Loading)
    val uiState: StateFlow<UIState<List<Quote>>> = _uiState

    fun getAllFavourites() {
        viewModelScope.launch {
            getAllFavouriteQuotesUseCase().collectLatest {
                when (it.isEmpty()) {
                    true -> _uiState.value = UIState.SuccessWithNoData
                    false -> _uiState.value = UIState.SuccessWithData(it)
                }
            }
        }
    }
}
