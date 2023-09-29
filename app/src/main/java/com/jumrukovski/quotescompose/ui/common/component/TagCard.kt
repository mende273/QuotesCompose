package com.jumrukovski.quotescompose.ui.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jumrukovski.quotescompose.data.model.middleware.Tag
import com.jumrukovski.quotescompose.ui.theme.PrimaryBackgroundColor
import com.jumrukovski.quotescompose.ui.theme.TertiaryColor
import com.jumrukovski.quotescompose.ui.theme.mediumTextStyle

@Composable
fun TagCard(tag: Tag, onItemClicked: (String) -> Unit) {
    Card(
        modifier = Modifier
            .background(color = PrimaryBackgroundColor)
            .fillMaxWidth()
            .clickable {
                onItemClicked(tag.name)
            },
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = TertiaryColor
        )
    ) {
        Text(
            modifier = Modifier
                .wrapContentSize()
                .padding(16.dp),
            text = tag.name,
            style = mediumTextStyle()
        )
    }
}
