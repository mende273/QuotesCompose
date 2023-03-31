package com.jumrukovski.quotescompose.ui.common.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jumrukovski.quotescompose.data.model.QuoteDTO
import com.jumrukovski.quotescompose.ui.theme.PrimaryTextColor
import com.jumrukovski.quotescompose.ui.theme.TertiaryColor

@Composable
fun SmallQuoteCard(quoteDTO: QuoteDTO, onNavigateToQuoteDetails: (QuoteDTO) -> Unit) {
    Box(modifier = Modifier
        .clickable { onNavigateToQuoteDetails(quoteDTO) }) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
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
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.PrimaryTextColor,
                        fontStyle = MaterialTheme.typography.bodyLarge.fontStyle,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Start
                    )
                )
            }
        }
    }
}