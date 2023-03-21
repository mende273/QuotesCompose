package com.jumrukovski.quotescompose.ui.screen.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jumrukovski.quotescompose.R
import com.jumrukovski.quotescompose.data.model.TagDTO
import com.jumrukovski.quotescompose.ui.common.Toolbar
import com.jumrukovski.quotescompose.ui.theme.PrimaryBackgroundColor
import com.jumrukovski.quotescompose.ui.theme.QuotesComposeTheme

@Composable
fun CategoriesScreen(
    viewModel: CategoriesViewModel,
    onNavigateToCategoryItems: (String) -> Unit
) {

    LaunchedEffect(key1 = "items") {
        viewModel.getAllTags()
    }

    val tagItems by viewModel.items.collectAsState()

    QuotesComposeTheme {
        Scaffold(
            topBar = { Toolbar(stringResource(id = R.string.screen_categories)) },
            content = { paddingValues ->
                CategoriesContents(
                    paddingValues = paddingValues,
                    tagItems = tagItems,
                    onNavigateToCategoryItems = onNavigateToCategoryItems
                )
            })
    }
}

@Composable
private fun CategoriesContents(
    paddingValues: PaddingValues,
    tagItems: List<TagDTO>,
    onNavigateToCategoryItems: (String) -> Unit
) {

    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.PrimaryBackgroundColor)
    ) {
        LazyColumn() {
            items(tagItems) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clickable {
                            onNavigateToCategoryItems(it.name)
                        }, text = it.name, color = Color.Black
                )
            }
        }
    }
}