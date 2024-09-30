package com.jumrukovski.quotescompose.ui.feature.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jumrukovski.quotescompose.R
import com.jumrukovski.quotescompose.domain.model.Quote
import com.jumrukovski.quotescompose.ui.common.component.FullSizeBox
import com.jumrukovski.quotescompose.ui.common.component.LargeQuoteCard
import com.jumrukovski.quotescompose.ui.common.component.TopBar
import com.jumrukovski.quotescompose.ui.menu.MenuItem
import com.jumrukovski.quotescompose.ui.preview.parameter.QuotePreviewParameter
import com.jumrukovski.quotescompose.ui.theme.QuotesComposeTheme

@Composable
fun QuoteDetailScreen(
    viewModel: QuoteDetailViewModel,
    quote: Quote,
    onNavigateBack: () -> Unit
) {
    val isFavorite by viewModel.isFavorite.collectAsStateWithLifecycle()

    var menuItem by remember {
        mutableStateOf(
            MenuItem(
                R.string.action_favourite,
                R.drawable.baseline_favorite_border_24
            )
        )
    }

    LaunchedEffect(key1 = quote.id, block = {
        viewModel.init(quote)
    })

    LaunchedEffect(key1 = isFavorite) {
        menuItem = menuItem.copy(
            icon =
            if (isFavorite) {
                R.drawable.baseline_favorite_24
            } else {
                R.drawable.baseline_favorite_border_24
            }
        )
    }

    ScreenContents(
        quote = quote,
        menuItem = menuItem,
        onToggleFavorite = viewModel::toggleFavourite,
        onNavigateBack = onNavigateBack
    )
}

@Composable
private fun ScreenContents(
    quote: Quote,
    menuItem: MenuItem? = null,
    onToggleFavorite: () -> Unit,
    onNavigateBack: () -> Unit
) {
    Column {
        TopBar(
            isBackButtonEnabled = true,
            onNavigateBack = onNavigateBack,
            menuItem = menuItem,
            onMenuItemClick = onToggleFavorite
        )

        FullSizeBox {
            LargeQuoteCard(quote.content, quote.author)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ScreenContentsPreview(
    @PreviewParameter(QuotePreviewParameter::class) quote: Quote
) {
    QuotesComposeTheme {
        ScreenContents(
            quote = quote,
            menuItem = MenuItem(
                R.string.action_favourite,
                R.drawable.baseline_favorite_border_24
            ),
            onToggleFavorite = {},
            onNavigateBack = {}
        )
    }
}
