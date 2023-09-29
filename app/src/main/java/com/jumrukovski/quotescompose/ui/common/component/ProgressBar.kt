package com.jumrukovski.quotescompose.ui.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jumrukovski.quotescompose.ui.theme.PrimaryBackgroundColor
import com.jumrukovski.quotescompose.ui.theme.PrimaryTextColor
import com.jumrukovski.quotescompose.ui.theme.TertiaryColor

@Composable
@Preview
fun ProgressBar() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryBackgroundColor),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = TertiaryColor,
            trackColor = PrimaryTextColor
        )
    }
}
