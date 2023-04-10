package com.jumrukovski.quotescompose.ui.screen.tags.selected

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
import com.jumrukovski.quotescompose.data.model.middleware.Quote
import com.jumrukovski.quotescompose.navigation.Screen
import com.jumrukovski.quotescompose.ui.common.TopBar
import com.jumrukovski.quotescompose.ui.common.component.ProgressBar
import com.jumrukovski.quotescompose.ui.common.component.SmallQuoteCard
import com.jumrukovski.quotescompose.ui.common.state.UIState
import com.jumrukovski.quotescompose.ui.theme.PrimaryBackgroundColor
import com.jumrukovski.quotescompose.ui.theme.QuotesComposeTheme

@Composable
fun SelectedTagScreen(
    viewModel: SelectedTagViewModel, tagName: String,
    onNavigateToQuoteDetails: (Quote) -> Unit,
    onNavigateBack: () -> Unit
) {
    val tagItems by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Screen.WithArguments.SelectedTag.ARGUMENT_TAG_NAME) {
        viewModel.getQuotesForTag(tagName)
    }

    QuotesComposeTheme {
        Scaffold(
            topBar = {
                TopBar(
                    title = stringResource(
                        id = R.string.screen_selected_tag_title,
                        tagName
                    ),
                    isBackButtonEnabled = true,
                    onNavigateBack = onNavigateBack
                )
            },
            content = { paddingValues ->
                Contents(paddingValues, tagItems, onNavigateToQuoteDetails)
            })
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
                            quote = quote,
                            onNavigateToQuoteDetails = {
                                onNavigateToQuoteDetails(it)
                            })
                    }
                }
            }
        }
    }
}