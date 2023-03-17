package com.jumrukovski.quotescompose.ui.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jumrukovski.quotescompose.data.model.QuoteDTO
import com.jumrukovski.quotescompose.ui.theme.PrimaryBackgroundColor

@Composable
fun QuoteDetailScreen(quoteDTO: QuoteDTO?){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.PrimaryBackgroundColor),
        contentAlignment = Alignment.Center
    ) {
        quoteDTO?.let {
            Text(text = quoteDTO.content)
        }
    }
}