package com.jumrukovski.quotescompose.ui.common.component

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.jumrukovski.quotescompose.ui.theme.QuotesComposeTheme
import com.jumrukovski.quotescompose.ui.theme.mediumTextStyle

@Composable
fun ErrorText(reason: String) {
    Text(
        modifier = Modifier.wrapContentSize(),
        text = reason,
        style = mediumTextStyle(),
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
private fun ErrorTextPreview() {
    QuotesComposeTheme {
        ErrorText(reason = "text to display")
    }
}
