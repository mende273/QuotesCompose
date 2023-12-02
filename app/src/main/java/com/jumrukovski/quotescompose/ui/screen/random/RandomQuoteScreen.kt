package com.jumrukovski.quotescompose.ui.screen.random

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jumrukovski.quotescompose.R
import com.jumrukovski.quotescompose.domain.model.MenuItem
import com.jumrukovski.quotescompose.domain.model.Quote
import com.jumrukovski.quotescompose.ui.common.component.FullSizeBox
import com.jumrukovski.quotescompose.ui.common.component.LargeQuoteCard
import com.jumrukovski.quotescompose.ui.common.component.TopBar
import com.jumrukovski.quotescompose.ui.common.component.UiStateWrapper
import com.jumrukovski.quotescompose.ui.common.state.UIState
import com.jumrukovski.quotescompose.ui.preview.parameter.QuotePreviewParameter
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

    LaunchedEffect(Unit) {
        viewModel.getRandomQuote()
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ScreenContents(
        uiState = uiState,
        menuItems = menuItems,
        onNavigateBack = { onNavigateBack() },
        onGetNewRandomQuote = { viewModel.getRandomQuote() }
    )
}

@Composable
private fun ScreenContents(
    uiState: UIState<Quote>,
    menuItems: List<MenuItem>,
    onNavigateBack: () -> Unit,
    onGetNewRandomQuote: () -> Unit
) {
    Column {
        TopBar(
            title = stringResource(id = R.string.screen_random_quote),
            menuItems = menuItems,
            isBackButtonEnabled = true,
            onNavigateBack = onNavigateBack,
            onMenuItemClick = {
                if (it.titleTextId == R.string.action_random) {
                    onGetNewRandomQuote()
                }
            }
        )

        UiStateWrapper(
            uiState = uiState,
            onSuccessWithData = { quote ->
                FullSizeBox {
                    LargeQuoteCard(quote.content, quote.author)
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ScreenContentsPreview(
    @PreviewParameter(QuotePreviewParameter::class) quote: Quote
) {
    QuotesComposeTheme {
        ScreenContents(
            uiState = UIState.SuccessWithData(quote),
            menuItems = listOf(
                MenuItem(
                    R.string.action_random,
                    R.drawable.baseline_refresh_24,
                    0,
                    false
                )
            ),
            onNavigateBack = { },
            onGetNewRandomQuote = {}
        )
    }
}
