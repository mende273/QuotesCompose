package com.jumrukovski.quotescompose.ui.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jumrukovski.quotescompose.ui.theme.PrimaryBackgroundColor
import com.jumrukovski.quotescompose.ui.theme.PrimaryTextColor
import com.jumrukovski.quotescompose.ui.theme.TertiaryColor

@Composable
fun LargeQuoteCard(content: String, author: String) {
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
                    text = content,
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.PrimaryTextColor,
                        fontStyle = MaterialTheme.typography.bodyLarge.fontStyle,
                        fontSize = 30.sp
                    )
                )
                Text(
                    modifier = Modifier
                        .wrapContentHeight(),
                    text = author,
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