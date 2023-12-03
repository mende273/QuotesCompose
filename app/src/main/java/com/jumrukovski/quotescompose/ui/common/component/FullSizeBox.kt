package com.jumrukovski.quotescompose.ui.common.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jumrukovski.quotescompose.ui.theme.spacing

@Composable
fun FullSizeBox(
    contentAlignment: Alignment = Alignment.Center,
    paddingValues: PaddingValues = PaddingValues(MaterialTheme.spacing.normal),
    contents: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentAlignment = contentAlignment
    ) {
        contents()
    }
}

@Preview
@Composable
private fun FullSizeBoxPreview() {
    FullSizeBox {
        Text(text = "contents go here")
    }
}
