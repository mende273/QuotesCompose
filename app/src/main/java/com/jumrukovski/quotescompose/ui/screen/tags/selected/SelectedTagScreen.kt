package com.jumrukovski.quotescompose.ui.screen.tags.selected

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
fun SelectedTagScreen(
    viewModel: SelectedTagViewModel,
    tagName: String,
    onNavigateToQuoteDetails: (Quote) -> Unit,
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = tagName) {
        viewModel.getQuotesForTag(tagName)
    }

    ScreenContents(
        tagName = tagName,
        uiState = uiState,
        onNavigateBack = { onNavigateBack() },
        onNavigateToQuoteDetails = { onNavigateToQuoteDetails(it) }
    )
}

@Composable
private fun ScreenContents(
    tagName: String,
    uiState: UIState<List<Quote>>,
    onNavigateBack: () -> Unit,
    onNavigateToQuoteDetails: (Quote) -> Unit
) {
    Column {
        TopBar(
            title = stringResource(
                id = R.string.screen_selected_tag_title,
                tagName
            ),
            isBackButtonEnabled = true,
            onNavigateBack = onNavigateBack
        )

        UiStateWrapper(
            uiState = uiState,
            onSuccessWithData = {
                FullSizeBox(contentAlignment = Alignment.TopCenter) {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(it) { quote ->
                            SmallQuoteCard(
                                quote = quote,
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
            tagName = "Famous Quotes",
            uiState = UIState.SuccessWithData(quotes),
            onNavigateBack = { },
            onNavigateToQuoteDetails = {}
        )
    }
}
