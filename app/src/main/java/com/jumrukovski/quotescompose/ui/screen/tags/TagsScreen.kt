package com.jumrukovski.quotescompose.ui.screen.tags

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jumrukovski.quotescompose.R
import com.jumrukovski.quotescompose.ui.common.component.TagCard
import com.jumrukovski.quotescompose.ui.common.component.TopBar
import com.jumrukovski.quotescompose.ui.common.component.UiStateWrapper

@Composable
fun TagsScreen(
    viewModel: TagsViewModel,
    onNavigateToSelectedTag: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column {
        TopBar(stringResource(id = R.string.screen_tags))

        UiStateWrapper(
            uiState = uiState,
            onSuccessWithData = { data ->
                LazyVerticalGrid(
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                    columns = GridCells.Adaptive(minSize = 150.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(data) { tag ->
                        TagCard(tag = tag, onItemClicked = { onNavigateToSelectedTag(it) })
                    }
                }
            }
        )
    }
}
