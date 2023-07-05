package com.jumrukovski.quotescompose.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumrukovski.quotescompose.data.model.middleware.Quote
import com.jumrukovski.quotescompose.domain.usecase.AddFavouriteQuoteToDBUseCase
import com.jumrukovski.quotescompose.domain.usecase.GetFavouriteQuoteUseCase
import com.jumrukovski.quotescompose.domain.usecase.RemoveQuoteFromFavouritesDBUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteDetailViewModel @Inject constructor(
    private val getFavouriteQuoteUseCase: GetFavouriteQuoteUseCase,
    private val addFavouriteQuoteToDBUseCase: AddFavouriteQuoteToDBUseCase,
    private val removeQuoteFromFavouritesDBUseCase: RemoveQuoteFromFavouritesDBUseCase
) :
    ViewModel() {

    fun checkIfQuoteIsInFavouritesDB(id: String): StateFlow<Quote?> {
        return getFavouriteQuoteUseCase(id).stateIn(
            scope = viewModelScope,
            initialValue = null,
            started = SharingStarted.WhileSubscribed(5_000)
        )
    }

    fun addQuoteToFavourites(id: String, content: String, author: String) {
        viewModelScope.launch(Dispatchers.IO) {
            addFavouriteQuoteToDBUseCase(id, content, author)
        }
    }

    fun removeQuoteFromFavourites(id: String, content: String, author: String) {
        viewModelScope.launch(Dispatchers.IO) {
            removeQuoteFromFavouritesDBUseCase(id, content, author)
        }
    }
}
