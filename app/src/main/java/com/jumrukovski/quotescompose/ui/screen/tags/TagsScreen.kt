package com.jumrukovski.quotescompose.ui.screen.tags

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jumrukovski.quotescompose.R
import com.jumrukovski.quotescompose.data.model.TagDTO
import com.jumrukovski.quotescompose.ui.common.Toolbar
import com.jumrukovski.quotescompose.ui.theme.PrimaryBackgroundColor
import com.jumrukovski.quotescompose.ui.theme.PrimaryTextColor
import com.jumrukovski.quotescompose.ui.theme.QuotesComposeTheme
import com.jumrukovski.quotescompose.ui.theme.TertiaryColor

@Composable
fun TagsScreen(
    viewModel: TagsViewModel,
    onNavigateToSelectedTag: (String) -> Unit
) {

    LaunchedEffect(key1 = "items") {
        viewModel.getAllTags()
    }

    val tagItems by viewModel.items.collectAsState()

    QuotesComposeTheme {
        Scaffold(
            topBar = { Toolbar(stringResource(id = R.string.screen_tags)) },
            content = { paddingValues ->
                Contents(
                    paddingValues = paddingValues,
                    tagItems = tagItems,
                    onNavigateToSelectedTag = onNavigateToSelectedTag
                )
            })
    }
}

@Composable
private fun Contents(
    paddingValues: PaddingValues,
    tagItems: List<TagDTO>,
    onNavigateToSelectedTag: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.PrimaryBackgroundColor)
    ) {
        LazyVerticalGrid(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            columns = GridCells.Adaptive(minSize = 150.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(tagItems) {
                Card(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .background(color = MaterialTheme.colorScheme.PrimaryBackgroundColor)
                        .fillMaxWidth()
                        .clickable {
                            onNavigateToSelectedTag(it.name)
                        },
                    shape = MaterialTheme.shapes.medium,
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.TertiaryColor,
                    )
                ) {
                    Box(modifier = Modifier.padding(16.dp)) {
                        Text(
                            modifier = Modifier
                                .wrapContentSize(),
                            text = it.name,
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.PrimaryTextColor,
                                fontStyle = MaterialTheme.typography.bodyLarge.fontStyle,
                                fontSize = 20.sp
                            )
                        )
                    }
                }
            }
        }
    }
}