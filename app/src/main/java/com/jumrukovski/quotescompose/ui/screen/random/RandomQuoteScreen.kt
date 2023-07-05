package com.jumrukovski.quotescompose.ui.screen.random

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jumrukovski.quotescompose.R
import com.jumrukovski.quotescompose.data.model.MenuItem
import com.jumrukovski.quotescompose.data.model.middleware.Quote
import com.jumrukovski.quotescompose.ui.common.component.EmptyDataCard
import com.jumrukovski.quotescompose.ui.common.component.LargeQuoteCard
import com.jumrukovski.quotescompose.ui.common.component.ProgressBar
import com.jumrukovski.quotescompose.ui.common.component.TopBar
import com.jumrukovski.quotescompose.ui.common.state.UIState

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

    LaunchedEffect(
        Unit,
        block = {
            viewModel.getRandomQuote()
        }
    )

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    Column {
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
        Contents(paddingValues = PaddingValues(), uiState.value)
    }
}

@Composable
private fun Contents(paddingValues: PaddingValues, uiState: UIState<Quote>) {
    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        when (uiState) {
            is UIState.Error -> EmptyDataCard(reason = stringResource(id = R.string.error))
            is UIState.Exception -> EmptyDataCard(reason = stringResource(id = R.string.error))
            UIState.Loading -> ProgressBar()
            UIState.SuccessWithNoData -> EmptyDataCard(reason = stringResource(id = R.string.no_data))
            is UIState.SuccessWithData -> LargeQuoteCard(uiState.data.content, uiState.data.author)
        }
    }
}
