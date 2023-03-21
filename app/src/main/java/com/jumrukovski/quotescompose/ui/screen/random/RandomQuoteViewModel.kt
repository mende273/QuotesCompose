package com.jumrukovski.quotescompose.ui.screen.random

import androidx.lifecycle.ViewModel
import com.jumrukovski.quotescompose.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RandomQuoteViewModel@Inject constructor(private val repository: Repository): ViewModel() {
}