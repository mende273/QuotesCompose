package com.jumrukovski.quotescompose.ui.screen.tags

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jumrukovski.quotescompose.R
import com.jumrukovski.quotescompose.domain.model.Tag
import com.jumrukovski.quotescompose.ui.common.component.EmptyDataCard
import com.jumrukovski.quotescompose.ui.common.component.ProgressBar
import com.jumrukovski.quotescompose.ui.common.component.TagCard
import com.jumrukovski.quotescompose.ui.common.component.TopBar
import com.jumrukovski.quotescompose.ui.common.state.UIState
import com.jumrukovski.quotescompose.ui.theme.PrimaryBackgroundColor

@Composable
fun TagsScreen(
    viewModel: TagsViewModel,
    onNavigateToSelectedTag: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = "items") {
        viewModel.getAllTags()
    }

    Column {
        TopBar(stringResource(id = R.string.screen_tags))
        Contents(
            paddingValues = PaddingValues(),
            uiState = uiState,
            onNavigateToSelectedTag = onNavigateToSelectedTag
        )
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
            .fillMaxSize()
            .background(PrimaryBackgroundColor)
            .padding(paddingValues)
    ) {
        when (uiState) {
            is UIState.Error -> EmptyDataCard(reason = stringResource(id = R.string.error))
            is UIState.Exception -> EmptyDataCard(reason = stringResource(id = R.string.error))
            is UIState.Loading -> ProgressBar()
            UIState.SuccessWithNoData -> EmptyDataCard(
                reason = stringResource(id = R.string.no_data)
            )
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
