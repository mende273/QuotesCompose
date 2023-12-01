package com.jumrukovski.quotescompose.ui.common.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jumrukovski.quotescompose.ui.theme.PrimaryBackgroundColor
import com.jumrukovski.quotescompose.ui.theme.QuotesComposeTheme
import com.jumrukovski.quotescompose.ui.theme.TertiaryColor
import com.jumrukovski.quotescompose.ui.theme.largeTextStyle
import com.jumrukovski.quotescompose.ui.theme.mediumTextStyle

@Composable
fun LargeQuoteCard(content: String, author: String) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .background(color = PrimaryBackgroundColor),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = TertiaryColor
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

@Preview
@Composable
private fun LargeQuoteCardPreview() {
    QuotesComposeTheme {
        LargeQuoteCard("this is a preview quote", "author name")
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LargeQuoteCardDarkPreview() {
    QuotesComposeTheme {
        LargeQuoteCard("this is a preview quote", "author name")
    }
}
