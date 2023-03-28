package com.jumrukovski.quotescompose.ui.screen.tags.selected

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jumrukovski.quotescompose.R
import com.jumrukovski.quotescompose.data.model.QuoteDTO
import com.jumrukovski.quotescompose.navigation.ScreenWithArgument
import com.jumrukovski.quotescompose.ui.common.Toolbar
import com.jumrukovski.quotescompose.ui.common.component.ProgressBar
import com.jumrukovski.quotescompose.ui.common.state.UIState
import com.jumrukovski.quotescompose.ui.theme.PrimaryBackgroundColor
import com.jumrukovski.quotescompose.ui.theme.PrimaryTextColor
import com.jumrukovski.quotescompose.ui.theme.QuotesComposeTheme
import com.jumrukovski.quotescompose.ui.theme.TertiaryColor

@Composable
fun SelectedTagScreen(
    viewModel: SelectedTagViewModel, tagName: String,
    onNavigateToQuoteDetails: (QuoteDTO) -> Unit,
    onNavigateBack: () -> Unit
) {
    val tagItems by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = ScreenWithArgument.SelectedTag.argument) {
        viewModel.getQuotesForTag(tagName)
    }

    QuotesComposeTheme {
        Scaffold(
            topBar = {
                Toolbar(
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
    state: UIState<List<QuoteDTO>>,
    onNavigateToQuoteDetails: (QuoteDTO) -> Unit
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
            is UIState.Loading -> { ProgressBar() }
            UIState.SuccessWithNoData -> ""
            is UIState.SuccessWithData -> {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    items(state.data) {
                        Box(modifier = Modifier
                            .clickable { onNavigateToQuoteDetails(it) }) {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.TertiaryColor,
                                )
                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .height(100.dp),
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = it.content,
                                        maxLines = 3,
                                        overflow = TextOverflow.Ellipsis,
                                        style = TextStyle(
                                            color = MaterialTheme.colorScheme.PrimaryTextColor,
                                            fontStyle = MaterialTheme.typography.bodyLarge.fontStyle,
                                            fontSize = 20.sp,
                                            textAlign = TextAlign.Start
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}