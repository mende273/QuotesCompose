package com.jumrukovski.quotescompose.ui.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumrukovski.quotescompose.domain.model.Quote
import com.jumrukovski.quotescompose.domain.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class QuoteDetailViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : ViewModel() {

    private var quote: Quote? = null

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> get() = _isFavorite

    fun init(quote: Quote) {
        this.quote = quote
        checkIsFavorite()
    }

    private fun checkIsFavorite() {
        viewModelScope.launch {
            quote?.let {
                localRepository.getFavouriteQuote(it.id)
                    .collectLatest { quote ->
                        _isFavorite.update { quote != null }
                    }
            }
        }
    }

    fun toggleFavourite() {
        viewModelScope.launch {
            quote?.let {
                when (_isFavorite.value) {
                    true -> localRepository.removeFavouriteQuote(it)

                    false -> localRepository.addFavouriteQuote(it)
                }
            }
        }
    }
}
