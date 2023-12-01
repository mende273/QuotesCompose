package com.jumrukovski.quotescompose.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jumrukovski.quotescompose.R
import com.jumrukovski.quotescompose.domain.model.MenuItem
import com.jumrukovski.quotescompose.domain.model.Quote
import com.jumrukovski.quotescompose.ui.common.component.FullSizeBox
import com.jumrukovski.quotescompose.ui.common.component.SmallQuoteCard
import com.jumrukovski.quotescompose.ui.common.component.TopBar
import com.jumrukovski.quotescompose.ui.common.component.UiStateWrapper

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onNavigateToQuoteDetails: (Quote) -> Unit,
    onNavigateToRandomQuote: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column {
        TopBar(
            title = stringResource(id = R.string.screen_home),
            menuItems = listOf(
                MenuItem(
                    R.string.action_random,
                    R.drawable.baseline_random,
                    0,
                    false
                )
            ),
            onMenuItemClick = {
                if (it.titleTextId == R.string.action_random) {
                    onNavigateToRandomQuote()
                }
            }
        )

        UiStateWrapper(
            uiState = uiState,
            onSuccessWithData = { data ->
                FullSizeBox(contentAlignment = Alignment.TopCenter) {
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        items(data) { quote ->
                            SmallQuoteCard(
                                quote = quote,
                                onNavigateToQuoteDetails = {
                                    onNavigateToQuoteDetails(it)
                                }
                            )
                        }
                    }
                }
            }
        )
    }
}
