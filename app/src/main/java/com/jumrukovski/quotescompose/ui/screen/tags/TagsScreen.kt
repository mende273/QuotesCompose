package com.jumrukovski.quotescompose.ui.screen.tags

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import com.jumrukovski.quotescompose.data.model.middleware.Tag
import com.jumrukovski.quotescompose.ui.common.component.TopBar
import com.jumrukovski.quotescompose.ui.common.component.ProgressBar
import com.jumrukovski.quotescompose.ui.common.component.TagCard
import com.jumrukovski.quotescompose.ui.common.state.UIState
import com.jumrukovski.quotescompose.ui.theme.PrimaryBackgroundColor
import com.jumrukovski.quotescompose.ui.theme.QuotesComposeTheme

@Composable
fun TagsScreen(
    viewModel: TagsViewModel,
    onNavigateToSelectedTag: (String) -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = "items") {
        viewModel.getAllTags()
    }

    QuotesComposeTheme {
        Scaffold(
            topBar = { TopBar(stringResource(id = R.string.screen_tags)) },
            content = { paddingValues ->
                Contents(
                    paddingValues = paddingValues,
                    uiState = uiState,
                    onNavigateToSelectedTag = onNavigateToSelectedTag
                )
            })
    }
}

@Composable
private fun Contents(
    paddingValues: PaddingValues,
    uiState: UIState<List<Tag>>,
    onNavigateToSelectedTag: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.PrimaryBackgroundColor)
    ) {
        when (uiState) {
            is UIState.Error -> ""
            is UIState.Exception -> ""
            is UIState.Loading -> {
                ProgressBar()
            }
            UIState.SuccessWithNoData -> ""
            is UIState.SuccessWithData -> {
                LazyVerticalGrid(
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                    columns = GridCells.Adaptive(minSize = 150.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(uiState.data) { tag ->
                        TagCard(tag = tag, onItemClicked = { onNavigateToSelectedTag(it) })
                    }
                }
            }
        }
    }
}