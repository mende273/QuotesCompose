package com.jumrukovski.quotescompose.ui.common.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.jumrukovski.quotescompose.domain.model.Quote
import com.jumrukovski.quotescompose.ui.preview.parameter.ListOfQuotesPreviewParameter
import com.jumrukovski.quotescompose.ui.theme.QuotesComposeTheme

@Composable
fun QuotesColumn(
    quotes: List<Quote>,
    onNavigateToQuoteDetails: (Quote) -> Unit
) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        items(quotes) { quote ->
            SmallQuoteCard(
                quote = quote,
                onNavigateToQuoteDetails = { onNavigateToQuoteDetails(it) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun QuotesColumnPreview(
    @PreviewParameter(ListOfQuotesPreviewParameter::class) quotes: List<Quote>
) {
    QuotesComposeTheme {
        QuotesColumn(quotes = quotes, onNavigateToQuoteDetails = {})
    }
}
