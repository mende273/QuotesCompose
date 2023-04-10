package com.jumrukovski.quotescompose.ui.screen.random

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jumrukovski.quotescompose.R
import com.jumrukovski.quotescompose.data.model.MenuItem
import com.jumrukovski.quotescompose.data.model.dto.QuoteDTO
import com.jumrukovski.quotescompose.ui.common.component.TopBar
import com.jumrukovski.quotescompose.ui.common.component.LargeQuoteCard
import com.jumrukovski.quotescompose.ui.common.component.ProgressBar
import com.jumrukovski.quotescompose.ui.common.state.UIState
import com.jumrukovski.quotescompose.ui.theme.QuotesComposeTheme

@Composable
fun RandomQuoteScreen(viewModel: RandomQuoteViewModel, onNavigateBack: () -> Unit) {

    val menuItems by remember {
        mutableStateOf(
            listOf(
                MenuItem(
                    R.string.action_random,
                    R.drawable.baseline_refresh_24,
                    0,
                    false
                )
            )
        )
    }

    LaunchedEffect(Unit,
        block = {
            viewModel.getRandomQuote()
        })

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    QuotesComposeTheme {
        Scaffold(
            topBar = {
                TopBar(
                    title = stringResource(id = R.string.screen_random_quote),
                    menuItems = menuItems,
                    isBackButtonEnabled = true,
                    onNavigateBack = onNavigateBack,
                    onMenuItemClick = {
                        if (it.titleTextId == R.string.action_random) {
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