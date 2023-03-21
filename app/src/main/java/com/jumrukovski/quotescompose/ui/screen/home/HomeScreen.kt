package com.jumrukovski.quotescompose.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.jumrukovski.quotescompose.R
import com.jumrukovski.quotescompose.data.model.QuoteDTO
import com.jumrukovski.quotescompose.ui.common.Toolbar
import com.jumrukovski.quotescompose.ui.screen.main.MainScreenMenuItem
import com.jumrukovski.quotescompose.ui.theme.PrimaryBackgroundColor
import com.jumrukovski.quotescompose.ui.theme.QuotesComposeTheme

@Composable
fun HomeScreen(onNavigateToQuoteDetails: (QuoteDTO) -> Unit,
               onNavigateToRandomQuote:() -> Unit) {
    QuotesComposeTheme {
        Scaffold(
            topBar = {
                Toolbar(
                    stringResource(id = R.string.screen_home),
                    MainScreenMenuItem.values().asList()
                ){
                    when(it){
                        MainScreenMenuItem.RANDOM -> onNavigateToRandomQuote()
                    }
                }
            },
            content = {paddingValues ->
                Box(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.PrimaryBackgroundColor),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Home Screen")
                }
            })
    }
}