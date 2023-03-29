package com.jumrukovski.quotescompose.ui.screen.random

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jumrukovski.quotescompose.R
import com.jumrukovski.quotescompose.data.model.QuoteDTO
import com.jumrukovski.quotescompose.ui.common.Toolbar
import com.jumrukovski.quotescompose.ui.common.component.ProgressBar
import com.jumrukovski.quotescompose.ui.common.state.UIState
import com.jumrukovski.quotescompose.ui.theme.PrimaryBackgroundColor
import com.jumrukovski.quotescompose.ui.theme.PrimaryTextColor
import com.jumrukovski.quotescompose.ui.theme.QuotesComposeTheme
import com.jumrukovski.quotescompose.ui.theme.TertiaryColor

@Composable
fun RandomQuoteScreen(viewModel: RandomQuoteViewModel, onNavigateBack: () -> Unit) {

    LaunchedEffect(key1 = "random_quote",
        block = {
        viewModel.getRandomQuote()
    })

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    QuotesComposeTheme {
        Scaffold(
            topBar = {
                Toolbar(
                    title = stringResource(id = R.string.screen_random_quote),
                    isBackButtonEnabled = true,
                    onNavigateBack = onNavigateBack
                )
            },
            content = { paddingValues ->
                Contents(paddingValues = paddingValues, uiState.value)
            })
    }
}

@Composable
private fun Contents(paddingValues: PaddingValues, uiState: UIState<QuoteDTO>) {
    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        when(uiState){
            is UIState.Error -> ""
            is UIState.Exception -> ""
            UIState.Loading -> ProgressBar()
            UIState.SuccessWithNoData -> ""
            is UIState.SuccessWithData -> {
                Card(
                    modifier = Modifier
                        .background(color = MaterialTheme.colorScheme.PrimaryBackgroundColor)
                        .padding(16.dp)
                        .fillMaxSize(),
                    shape = MaterialTheme.shapes.medium,
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.TertiaryColor,
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column {
                            Text(
                                modifier = Modifier
                                    .wrapContentHeight(),
                                text = uiState.data.content,
                                style = TextStyle(
                                    color = MaterialTheme.colorScheme.PrimaryTextColor,
                                    fontStyle = MaterialTheme.typography.bodyLarge.fontStyle,
                                    fontSize = 30.sp
                                )
                            )
                            Text(
                                modifier = Modifier
                                    .wrapContentHeight(),
                                text = uiState.data.author,
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
}