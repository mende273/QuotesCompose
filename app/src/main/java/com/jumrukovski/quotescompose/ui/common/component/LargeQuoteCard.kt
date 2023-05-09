package com.jumrukovski.quotescompose.ui.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jumrukovski.quotescompose.ui.theme.PrimaryBackgroundColor
import com.jumrukovski.quotescompose.ui.theme.TertiaryColor
import com.jumrukovski.quotescompose.ui.theme.largeTextStyle
import com.jumrukovski.quotescompose.ui.theme.mediumTextStyle

@Composable
fun LargeQuoteCard(content: String, author: String) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.PrimaryBackgroundColor)
            .padding(16.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.TertiaryColor,
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier
                    .wrapContentHeight(),
                text = content,
                style = largeTextStyle()
            )
            Text(
                modifier = Modifier
                    .wrapContentHeight(),
                text = author,
                style = mediumTextStyle()
            )
        }
    }
}