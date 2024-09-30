package com.jumrukovski.quotescompose.ui.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumrukovski.quotescompose.domain.model.Quote
import com.jumrukovski.quotescompose.domain.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteDetailViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS: Long = 5_000
    }

    fun checkIfQuoteIsInFavouritesDB(id: Int): StateFlow<Quote?> {
        return localRepository.getFavouriteQuote(id).stateIn(
            scope = viewModelScope,
            initialValue = null,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS)
        )
    }

    fun addQuoteToFavourites(quote: Quote) {
        viewModelScope.launch(Dispatchers.IO) {
            localRepository.addFavouriteQuote(quote)
        }
    }

    fun removeQuoteFromFavourites(quote: Quote) {
        viewModelScope.launch(Dispatchers.IO) {
            localRepository.removeFavouriteQuote(quote)
        }
    }
}
