package com.jumrukovski.quotescompose.ui.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jumrukovski.quotescompose.ui.theme.PrimaryBackgroundColor

@Composable
fun QuoteDetailScreen(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.PrimaryBackgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Quote detail Screen")
    }
}