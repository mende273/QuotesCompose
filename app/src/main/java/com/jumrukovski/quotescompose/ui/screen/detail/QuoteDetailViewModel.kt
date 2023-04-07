package com.jumrukovski.quotescompose.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumrukovski.quotescompose.data.model.entity.FavouriteQuoteEntity
import com.jumrukovski.quotescompose.data.model.entity.mapToFavouriteQuoteEntity
import com.jumrukovski.quotescompose.data.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteDetailViewModel @Inject constructor(private val localRepository: LocalRepository) :
    ViewModel() {

    fun checkIfQuoteIsInFavouritesDB(id: String): StateFlow<FavouriteQuoteEntity?> {
        return localRepository.getFavouriteQuoteAsync(id).stateIn(
            scope = viewModelScope,
            initialValue = null,
            started = SharingStarted.WhileSubscribed(5_000)
        )
    }

    fun addQuoteToFavourites(id: String, content: String, author: String) {
        val entity = mapToFavouriteQuoteEntity(id, content, author)

        viewModelScope.launch(Dispatchers.IO) {
            localRepository.addFavouriteQuote(entity)
        }
    }

    fun removeQuoteFromFavourites(id: String, content: String, author: String) {
        val entity = mapToFavouriteQuoteEntity(id, content, author)

        viewModelScope.launch(Dispatchers.IO) {
            localRepository.removeFavouriteQuote(entity)
        }
    }
}