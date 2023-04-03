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
import com.jumrukovski.quotescompose.data.model.TagDTO
import com.jumrukovski.quotescompose.ui.theme.PrimaryBackgroundColor
import com.jumrukovski.quotescompose.ui.theme.TertiaryColor
import com.jumrukovski.quotescompose.ui.theme.mediumTextStyle

@Composable
fun TagCard(tagDTO: TagDTO, onItemClicked: (String) -> Unit) {
    Card(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.PrimaryBackgroundColor)
            .fillMaxWidth()
            .clickable {
                onItemClicked(tagDTO.name)
            },
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.TertiaryColor,
        )
    ) {
        Text(
            modifier = Modifier
                .wrapContentSize()
                .padding(16.dp),
            text = tagDTO.name,
            style = mediumTextStyle()
        )
    }
}