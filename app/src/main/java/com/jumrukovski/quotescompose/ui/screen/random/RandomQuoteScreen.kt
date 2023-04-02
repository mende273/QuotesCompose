package com.jumrukovski.quotescompose.ui.screen.random

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jumrukovski.quotescompose.R
import com.jumrukovski.quotescompose.data.model.QuoteDTO
import com.jumrukovski.quotescompose.navigation.RandomQuoteMenuItem
import com.jumrukovski.quotescompose.ui.common.TopBar
import com.jumrukovski.quotescompose.ui.common.component.LargeQuoteCard
import com.jumrukovski.quotescompose.ui.common.component.ProgressBar
import com.jumrukovski.quotescompose.ui.common.state.UIState
import com.jumrukovski.quotescompose.ui.theme.QuotesComposeTheme

@Composable
fun RandomQuoteScreen(viewModel: RandomQuoteViewModel, onNavigateBack: () -> Unit) {

    LaunchedEffect(key1 = "random_quote",
        block = {
            viewModel.getRandomQuote()
        })

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    QuotesComposeTheme {
        Scaffold(
            topBar = {
                TopBar(
                    title = stringResource(id = R.string.screen_random_quote),
                    menuItems = RandomQuoteMenuItem.values().asList(),
                    isBackButtonEnabled = true,
                    onNavigateBack = onNavigateBack,
                    onMenuItemClick = {
                        if(it == RandomQuoteMenuItem.RANDOM){
                            viewModel.getRandomQuote()
                        }
                    }
                )
            },
            content = { paddingValues ->
                Contents(paddingValues = paddingValues, uiState.value)
            })
    }
}

@Composable
private fun Contents(paddingValues: PaddingValues, uiState: UIState<QuoteDTO>) {
    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        when (uiState) {
            is UIState.Error -> ""
            is UIState.Exception -> ""
            UIState.Loading -> ProgressBar()
            UIState.SuccessWithNoData -> ""
            is UIState.SuccessWithData -> {
                LargeQuoteCard(uiState.data.content, uiState.data.author)
            }
        }
    }
}