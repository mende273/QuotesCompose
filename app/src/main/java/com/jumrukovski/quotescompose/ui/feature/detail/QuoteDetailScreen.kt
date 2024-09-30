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
import com.jumrukovski.quotescompose.R
import com.jumrukovski.quotescompose.domain.model.Quote
import com.jumrukovski.quotescompose.ui.common.component.FullSizeBox
import com.jumrukovski.quotescompose.ui.common.component.LargeQuoteCard
import com.jumrukovski.quotescompose.ui.common.component.TopBar
import com.jumrukovski.quotescompose.ui.menu.MenuItem
import com.jumrukovski.quotescompose.ui.preview.parameter.QuotePreviewParameter
import com.jumrukovski.quotescompose.ui.theme.QuotesComposeTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun QuoteDetailScreen(
    viewModel: QuoteDetailViewModel,
    quote: Quote,
    onNavigateBack: () -> Unit
) {
    var isFavourite by remember { mutableStateOf<Quote?>(null) }

    var menuItems by remember {
        mutableStateOf(
            listOf(
                MenuItem(
                    R.string.action_favourite,
                    R.drawable.baseline_favorite_border_24,
                    R.drawable.baseline_favorite_24,
                    false
                )
            )
        )
    }

    LaunchedEffect(key1 = quote.id, block = {
        viewModel.checkIfQuoteIsInFavouritesDB(quote.id).collectLatest {
            isFavourite = it

            menuItems = menuItems.map { item ->
                if (item.titleTextId == R.string.action_favourite) {
                    item.copy(isSelected = it != null)
                } else {
                    item
                }
            }
        }
    })

    ScreenContents(
        quote = quote,
        isFavourite = isFavourite != null,
        menuItems = menuItems,
        onAddQuoteToFavourites = viewModel::addQuoteToFavourites,
        onRemoveQuoteFromFavourites = viewModel::removeQuoteFromFavourites,
        onNavigateBack = { onNavigateBack() }
    )
}

@Composable
private fun ScreenContents(
    quote: Quote,
    isFavourite: Boolean,
    menuItems: List<MenuItem>,
    onAddQuoteToFavourites: (Quote) -> Unit,
    onRemoveQuoteFromFavourites: (Quote) -> Unit,
    onNavigateBack: () -> Unit
) {
    Column {
        TopBar(
            isBackButtonEnabled = true,
            onNavigateBack = onNavigateBack,
            menuItems = menuItems,
            onMenuItemClick = {
                if (it.titleTextId == R.string.action_favourite) {
                    when (isFavourite) {
                        true -> onRemoveQuoteFromFavourites(quote)
                        false -> onAddQuoteToFavourites(quote)
                    }
                }
            }
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
            isFavourite = false,
            menuItems = listOf(
                MenuItem(
                    R.string.action_favourite,
                    R.drawable.baseline_favorite_border_24,
                    R.drawable.baseline_favorite_24,
                    false
                )
            ),
            onAddQuoteToFavourites = {},
            onRemoveQuoteFromFavourites = {},
            onNavigateBack = {}
        )
    }
}
