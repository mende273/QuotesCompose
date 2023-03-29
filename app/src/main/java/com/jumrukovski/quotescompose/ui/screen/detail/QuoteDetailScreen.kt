package com.jumrukovski.quotescompose.ui.screen.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jumrukovski.quotescompose.data.model.QuoteDTO
import com.jumrukovski.quotescompose.ui.common.Toolbar
import com.jumrukovski.quotescompose.ui.common.component.LargeQuoteCard
import com.jumrukovski.quotescompose.ui.theme.QuotesComposeTheme

@Composable
fun QuoteDetailScreen(quoteDTO: QuoteDTO?, onNavigateBack: () -> Unit) {
    QuotesComposeTheme {
        Scaffold(
            topBar = {
                Toolbar(
                    isBackButtonEnabled = true,
                    onNavigateBack = onNavigateBack
                )
            },
            content = { paddingValues ->
                Contents(paddingValues = paddingValues, quoteDTO)
            })
    }
}

@Composable
private fun Contents(paddingValues: PaddingValues, quoteDTO: QuoteDTO?) {
    quoteDTO?.let {
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            LargeQuoteCard(quoteDTO = it)
        }
    }
}