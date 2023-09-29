package com.jumrukovski.quotescompose.ui.screen.tags.selected

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jumrukovski.quotescompose.R
import com.jumrukovski.quotescompose.data.model.middleware.Quote
import com.jumrukovski.quotescompose.ui.common.component.EmptyDataCard
import com.jumrukovski.quotescompose.ui.common.component.ProgressBar
import com.jumrukovski.quotescompose.ui.common.component.SmallQuoteCard
import com.jumrukovski.quotescompose.ui.common.component.TopBar
import com.jumrukovski.quotescompose.ui.common.state.UIState
import com.jumrukovski.quotescompose.ui.theme.PrimaryBackgroundColor

@Composable
fun SelectedTagScreen(
    viewModel: SelectedTagViewModel,
    tagName: String,
    onNavigateToQuoteDetails: (Quote) -> Unit,
    onNavigateBack: () -> Unit
) {
    val tagItems by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = tagName) {
        viewModel.getQuotesForTag(tagName)
    }

    Column {
        TopBar(
            title = stringResource(
                id = R.string.screen_selected_tag_title,
                tagName
            ),
            isBackButtonEnabled = true,
            onNavigateBack = onNavigateBack
        )
        Contents(paddingValues = PaddingValues(16.dp), tagItems, onNavigateToQuoteDetails)
    }
}

@Composable
private fun Contents(
    paddingValues: PaddingValues,
    state: UIState<List<Quote>>,
    onNavigateToQuoteDetails: (Quote) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryBackgroundColor)
            .padding(paddingValues)
    ) {
        when (state) {
            is UIState.Error -> EmptyDataCard(reason = stringResource(id = R.string.error))
            is UIState.Exception -> EmptyDataCard(reason = stringResource(id = R.string.error))
            is UIState.Loading -> ProgressBar()
            UIState.SuccessWithNoData -> EmptyDataCard(
                reason = stringResource(id = R.string.no_data)
            )
            is UIState.SuccessWithData -> {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    items(state.data) { quote ->
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
    }
}
