package com.jumrukovski.quotescompose.ui.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jumrukovski.quotescompose.data.model.QuoteDTO
import com.jumrukovski.quotescompose.ui.common.Toolbar
import com.jumrukovski.quotescompose.ui.theme.*

@Composable
fun QuoteDetailScreen(quoteDTO: QuoteDTO?,onNavigateBack: () ->Unit) {
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
            Card(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.PrimaryBackgroundColor)
                    .padding(16.dp)
                    .fillMaxSize(),
                shape = MaterialTheme.shapes.medium,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.TertiaryColor,
                )
            ) {
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column {
                        Text(
                            modifier = Modifier
                                .wrapContentHeight(),
                            text = quoteDTO.content,
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.PrimaryTextColor,
                                fontStyle = MaterialTheme.typography.bodyLarge.fontStyle,
                                fontSize = 30.sp
                            )
                        )
                        Text(
                            modifier = Modifier
                                .wrapContentHeight(),
                            text = quoteDTO.author,
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.PrimaryTextColor,
                                fontStyle = MaterialTheme.typography.bodyLarge.fontStyle,
                                fontSize = 20.sp
                            )
                        )
                    }
                }
            }
        }
    }
}