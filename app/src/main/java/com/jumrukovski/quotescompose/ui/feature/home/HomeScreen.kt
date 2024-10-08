package com.jumrukovski.quotescompose.ui.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jumrukovski.quotescompose.R
import com.jumrukovski.quotescompose.domain.model.Quote
import com.jumrukovski.quotescompose.theme.QuotesComposeTheme
import com.jumrukovski.quotescompose.ui.common.component.FullSizeBox
import com.jumrukovski.quotescompose.ui.common.component.QuotesColumn
import com.jumrukovski.quotescompose.ui.common.component.TopBar
import com.jumrukovski.quotescompose.ui.common.component.UiStateWrapper
import com.jumrukovski.quotescompose.ui.common.menu.MenuItem
import com.jumrukovski.quotescompose.ui.common.preview.parameter.ListOfQuotesPreviewParameter
import com.jumrukovski.quotescompose.ui.common.state.UIState

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    onNavigateToQuoteDetails: (Quote) -> Unit,
    onNavigateToRandomQuote: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ScreenContents(
        modifier = modifier,
        uiState = uiState,
        onNavigateToRandomQuote = onNavigateToRandomQuote,
        onNavigateToQuoteDetails = onNavigateToQuoteDetails
    )
}

@Composable
private fun ScreenContents(
    modifier: Modifier,
    uiState: UIState<List<Quote>>,
    onNavigateToRandomQuote: () -> Unit,
    onNavigateToQuoteDetails: (Quote) -> Unit
) {
    Column(modifier = modifier) {
        TopBar(
            title = stringResource(id = R.string.screen_home),
            menuItem = MenuItem(
                R.string.action_random,
                R.drawable.baseline_random
            ),
            onMenuItemClick = onNavigateToRandomQuote
        )

        UiStateWrapper(
            uiState = uiState,
            onSuccessWithData = { data ->
                FullSizeBox(contentAlignment = Alignment.TopCenter) {
                    QuotesColumn(
                        quotes = data,
                        onNavigateToQuoteDetails = onNavigateToQuoteDetails
                    )
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ScreenContentsPreview(
    @PreviewParameter(ListOfQuotesPreviewParameter::class) quotes: List<Quote>
) {
    QuotesComposeTheme {
        ScreenContents(
            modifier = Modifier,
            uiState = UIState.SuccessWithData(quotes),
            onNavigateToRandomQuote = { },
            onNavigateToQuoteDetails = {}
        )
    }
}
