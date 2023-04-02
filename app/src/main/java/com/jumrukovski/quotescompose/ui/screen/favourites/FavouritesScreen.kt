package com.jumrukovski.quotescompose.ui.screen.favourites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.jumrukovski.quotescompose.R
import com.jumrukovski.quotescompose.ui.common.AppBar
import com.jumrukovski.quotescompose.ui.theme.PrimaryBackgroundColor
import com.jumrukovski.quotescompose.ui.theme.QuotesComposeTheme

@Composable
fun FavouritesScreen(navHostController: NavHostController) {
    QuotesComposeTheme {
        Scaffold(
            topBar = {
                AppBar(
                    stringResource(id = R.string.screen_favourites)
                )
            },
            content = { paddingValues ->
                Contents(paddingValues = paddingValues)
            })
    }
}

@Composable
private fun Contents(paddingValues: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(MaterialTheme.colorScheme.PrimaryBackgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Favourites screen")
    }
}