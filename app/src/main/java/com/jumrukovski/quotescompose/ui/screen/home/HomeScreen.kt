package com.jumrukovski.quotescompose.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jumrukovski.quotescompose.R
import com.jumrukovski.quotescompose.data.model.MenuItem
import com.jumrukovski.quotescompose.data.model.dto.QuoteDTO
import com.jumrukovski.quotescompose.ui.common.TopBar
import com.jumrukovski.quotescompose.ui.common.component.ProgressBar
import com.jumrukovski.quotescompose.ui.common.component.SmallQuoteCard
import com.jumrukovski.quotescompose.ui.common.state.UIState
import com.jumrukovski.quotescompose.ui.theme.PrimaryBackgroundColor
import com.jumrukovski.quotescompose.ui.theme.QuotesComposeTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel, onNavigateToQuoteDetails: (QuoteDTO) -> Unit,
    onNavigateToRandomQuote: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = "home") {
        viewModel.getQuotes()
    }

    QuotesComposeTheme {
        Scaffold(
            topBar = {
                TopBar(
                    title = stringResource(id = R.string.screen_home),
                    menuItems = listOf(
                        MenuItem(
                            R.string.action_random,
                            R.drawable.baseline_random,
                            0,
                            false
                        )
                    ),
                    onMenuItemClick = {
                        if (it.titleTextId == R.string.action_random) {
                            onNavigateToRandomQuote()
                        }
                    }
                )
            },
            content = { paddingValues ->
                Contents(paddingValues = paddingValues, state = uiState, onItemClicked = {
                    onNavigateToQuoteDetails(it)
                })
            })
    }
}

@Composable
private fun Contents(
    paddingValues: PaddingValues,
    state: UIState<List<QuoteDTO>>,
    onItemClicked: (QuoteDTO) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(MaterialTheme.colorScheme.PrimaryBackgroundColor)
            .padding(16.dp)
    ) {
        when (state) {
            is UIState.Error -> ""
            is UIState.Exception -> ""
            is UIState.Loading -> {
                ProgressBar()
            }
            UIState.SuccessWithNoData -> ""
            is UIState.SuccessWithData -> {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    items(state.data) { quote ->
                        SmallQuoteCard(
                            quoteDTO = quote,
                            onNavigateToQuoteDetails = {
                                onItemClicked(it)
                            })
                    }
                }
            }
        }
    }
}