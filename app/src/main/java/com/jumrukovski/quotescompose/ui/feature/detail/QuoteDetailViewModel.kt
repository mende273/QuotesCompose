package com.jumrukovski.quotescompose.ui.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumrukovski.quotescompose.domain.model.Quote
import com.jumrukovski.quotescompose.domain.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class QuoteDetailViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS: Long = 5_000
    }

    fun checkIfQuoteIsInFavouritesDB(id: String): StateFlow<Quote?> {
        return localRepository.getFavouriteQuoteAsync(id).stateIn(
            scope = viewModelScope,
            initialValue = null,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS)
        )
    }

    fun addQuoteToFavourites(id: String, content: String, author: String) {
        viewModelScope.launch(Dispatchers.IO) {
            localRepository.addFavouriteQuote(id, content, author)
        }
    }

    fun removeQuoteFromFavourites(id: String, content: String, author: String) {
        viewModelScope.launch(Dispatchers.IO) {
            localRepository.removeFavouriteQuote(id, content, author)
        }
    }
}
