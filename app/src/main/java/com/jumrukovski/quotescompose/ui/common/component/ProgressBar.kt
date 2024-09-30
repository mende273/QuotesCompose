package com.jumrukovski.quotescompose.ui.common.component

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.jumrukovski.quotescompose.theme.PrimaryTextColor
import com.jumrukovski.quotescompose.theme.TertiaryColor

@Composable
@Preview
fun ProgressBar() {
    CircularProgressIndicator(
        color = TertiaryColor,
        trackColor = PrimaryTextColor
    )
}
