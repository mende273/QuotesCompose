package com.jumrukovski.quotescompose.ui.screen.favourites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jumrukovski.quotescompose.R
import com.jumrukovski.quotescompose.domain.model.Quote
import com.jumrukovski.quotescompose.ui.common.component.FullSizeBox
import com.jumrukovski.quotescompose.ui.common.component.SmallQuoteCard
import com.jumrukovski.quotescompose.ui.common.component.TopBar
import com.jumrukovski.quotescompose.ui.common.component.UiStateWrapper
import com.jumrukovski.quotescompose.ui.common.state.UIState
import com.jumrukovski.quotescompose.ui.preview.parameter.ListOfQuotesPreviewParameter
import com.jumrukovski.quotescompose.ui.theme.QuotesComposeTheme

@Composable
fun FavouritesScreen(
    viewModel: FavouritesViewModel,
    onNavigateToQuoteDetails: (Quote) -> Unit,
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ScreenContents(
        uiState = uiState,
        onNavigateToQuoteDetails = { onNavigateToQuoteDetails(it) },
        onNavigateBack = { onNavigateBack() }
    )
}

@Composable
private fun ScreenContents(
    uiState: UIState<List<Quote>>,
    onNavigateToQuoteDetails: (Quote) -> Unit,
    onNavigateBack: () -> Unit
) {
    Column {
        TopBar(
            title = stringResource(id = R.string.screen_favourites),
            isBackButtonEnabled = true,
            onNavigateBack = onNavigateBack
        )

        UiStateWrapper(
            uiState = uiState,
            onSuccessWithData = { data ->
                FullSizeBox(contentAlignment = Alignment.TopCenter) {
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        items(data) { quote ->
                            SmallQuoteCard(
                                quote = Quote(quote.id, quote.content, quote.author),
                                onNavigateToQuoteDetails = {
                                    onNavigateToQuoteDetails(it)
                                }
                            )
                        }
                    }
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
            uiState = UIState.SuccessWithData(quotes),
            onNavigateToQuoteDetails = {},
            onNavigateBack = {}
        )
    }
}
