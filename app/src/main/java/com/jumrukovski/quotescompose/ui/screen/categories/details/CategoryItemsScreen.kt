package com.jumrukovski.quotescompose.ui.screen.categories.details

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jumrukovski.quotescompose.R
import com.jumrukovski.quotescompose.data.model.QuoteDTO
import com.jumrukovski.quotescompose.navigation.ScreenWithArgument
import com.jumrukovski.quotescompose.ui.common.Toolbar
import com.jumrukovski.quotescompose.ui.theme.PrimaryBackgroundColor
import com.jumrukovski.quotescompose.ui.theme.QuotesComposeTheme

@Composable
fun CategoryItemsScreen(
    viewModel: CategoryItemsViewModel, categoryName: String,
    onNavigateToQuoteDetails: (QuoteDTO) -> Unit
) {
    val categoryItems by viewModel.items.collectAsState()

    LaunchedEffect(key1 = ScreenWithArgument.CategoryDetail.argument) {
        viewModel.getQuotesForTag(categoryName)
    }

    QuotesComposeTheme {
        Scaffold(
            topBar = {
                Toolbar(
                    stringResource(
                        id = R.string.screen_category_items_title,
                        categoryName
                    )
                )
            },
            content = { paddingValues ->
                Contents(paddingValues, categoryItems, onNavigateToQuoteDetails)
            })
    }
}

@Composable
private fun Contents(
    paddingValues: PaddingValues,
    categoryItems: List<QuoteDTO>,
    onNavigateToQuoteDetails: (QuoteDTO) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .background(MaterialTheme.colorScheme.PrimaryBackgroundColor)
            .padding(16.dp)
    ) {
        LazyColumn {
            items(categoryItems) {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .clickable { onNavigateToQuoteDetails(it) }) {
                    Column {
                        Text(text = it.content)
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }
        }
    }
}