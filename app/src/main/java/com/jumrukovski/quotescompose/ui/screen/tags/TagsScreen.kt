package com.jumrukovski.quotescompose.ui.screen.tags

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jumrukovski.quotescompose.R
import com.jumrukovski.quotescompose.domain.model.Tag
import com.jumrukovski.quotescompose.ui.common.component.FullSizeBox
import com.jumrukovski.quotescompose.ui.common.component.TagCard
import com.jumrukovski.quotescompose.ui.common.component.TopBar
import com.jumrukovski.quotescompose.ui.common.component.UiStateWrapper
import com.jumrukovski.quotescompose.ui.common.state.UIState
import com.jumrukovski.quotescompose.ui.preview.parameter.ListOfTagsPreviewParameter
import com.jumrukovski.quotescompose.ui.theme.QuotesComposeTheme
import com.jumrukovski.quotescompose.ui.theme.spacing

@Composable
fun TagsScreen(
    viewModel: TagsViewModel,
    onNavigateToSelectedTag: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ScreenContents(
        uiState = uiState,
        onNavigateToSelectedTag = { onNavigateToSelectedTag(it) }
    )
}

@Composable
private fun ScreenContents(
    uiState: UIState<List<Tag>>,
    onNavigateToSelectedTag: (String) -> Unit
) {
    Column {
        TopBar(stringResource(id = R.string.screen_tags))

        UiStateWrapper(
            uiState = uiState,
            onSuccessWithData = { data ->
                FullSizeBox(contentAlignment = Alignment.TopCenter) {
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(minSize = 150.dp),
                        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.normal),
                        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.normal)
                    ) {
                        items(data) { tag ->
                            TagCard(tag = tag, onItemClicked = { onNavigateToSelectedTag(it) })
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
    @PreviewParameter(ListOfTagsPreviewParameter::class) tags: List<Tag>
) {
    QuotesComposeTheme {
        ScreenContents(
            uiState = UIState.SuccessWithData(tags),
            onNavigateToSelectedTag = {}
        )
    }
}
