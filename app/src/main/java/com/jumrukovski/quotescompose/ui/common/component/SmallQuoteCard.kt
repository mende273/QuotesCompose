package com.jumrukovski.quotescompose.ui.common.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.jumrukovski.quotescompose.data.model.dto.QuoteDTO
import com.jumrukovski.quotescompose.ui.theme.TertiaryColor
import com.jumrukovski.quotescompose.ui.theme.mediumTextStyle

@Composable
fun SmallQuoteCard(quoteDTO: QuoteDTO, onNavigateToQuoteDetails: (QuoteDTO) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onNavigateToQuoteDetails(quoteDTO) },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.TertiaryColor,
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .height(100.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = quoteDTO.content,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = mediumTextStyle()
            )
        }
    }
}