package com.jumrukovski.quotescompose.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun largeTextStyle(): TextStyle {
    return TextStyle(
        color = MaterialTheme.colorScheme.PrimaryTextColor,
        fontStyle = MaterialTheme.typography.bodyLarge.fontStyle,
        fontSize = 30.sp,
        textAlign = TextAlign.Start
    )
}

@Composable
fun mediumTextStyle(): TextStyle {
    return TextStyle(
        color = MaterialTheme.colorScheme.PrimaryTextColor,
        fontStyle = MaterialTheme.typography.bodyLarge.fontStyle,
        fontSize = 20.sp,
        textAlign = TextAlign.Start
    )
}
