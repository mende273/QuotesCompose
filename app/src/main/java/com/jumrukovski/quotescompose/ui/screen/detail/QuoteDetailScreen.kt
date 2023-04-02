package com.jumrukovski.quotescompose.ui.screen.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jumrukovski.quotescompose.ui.common.AppBar
import com.jumrukovski.quotescompose.ui.common.component.LargeQuoteCard
import com.jumrukovski.quotescompose.ui.theme.QuotesComposeTheme

@Composable
fun QuoteDetailScreen(id: String, content: String, author: String, onNavigateBack: () -> Unit) {
    QuotesComposeTheme {
        Scaffold(
            topBar = {
                AppBar(
                    isBackButtonEnabled = true,
                    onNavigateBack = onNavigateBack
                )
            },
            content = { paddingValues ->
                Contents(paddingValues = paddingValues, content, author)
            })
    }
}

@Composable
private fun Contents(paddingValues: PaddingValues, content: String, author: String) {
    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        LargeQuoteCard(content, author)
    }
}