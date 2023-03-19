package com.jumrukovski.quotescompose.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jumrukovski.quotescompose.data.model.QuoteDTO
import com.jumrukovski.quotescompose.ui.theme.PrimaryBackgroundColor

@Composable
fun HomeScreen(onNavigateToQuoteDetails: (QuoteDTO) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.PrimaryBackgroundColor)
    ) {
        //todo
    }
}