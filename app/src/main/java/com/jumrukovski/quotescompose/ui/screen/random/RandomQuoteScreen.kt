package com.jumrukovski.quotescompose.ui.screen.random

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jumrukovski.quotescompose.R
import com.jumrukovski.quotescompose.domain.model.MenuItem
import com.jumrukovski.quotescompose.ui.common.component.FullSizeBox
import com.jumrukovski.quotescompose.ui.common.component.LargeQuoteCard
import com.jumrukovski.quotescompose.ui.common.component.TopBar
import com.jumrukovski.quotescompose.ui.common.component.UiStateWrapper

@Composable
fun RandomQuoteScreen(viewModel: RandomQuoteViewModel, onNavigateBack: () -> Unit) {
    val menuItems by remember {
        mutableStateOf(
            listOf(
                MenuItem(
                    R.string.action_random,
                    R.drawable.baseline_refresh_24,
                    0,
                    false
                )
            )
        )
    }

    LaunchedEffect(
        Unit,
        block = {
            viewModel.getRandomQuote()
        }
    )

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column {
        TopBar(
            title = stringResource(id = R.string.screen_random_quote),
            menuItems = menuItems,
            isBackButtonEnabled = true,
            onNavigateBack = onNavigateBack,
            onMenuItemClick = {
                if (it.titleTextId == R.string.action_random) {
                    viewModel.getRandomQuote()
                }
            }
        )

        UiStateWrapper(
            uiState = uiState,
            onSuccessWithData = { quote ->
                FullSizeBox {
                    LargeQuoteCard(quote.content, quote.author)
                }
            }
        )
    }
}
