package com.jumrukovski.quotescompose.ui.common.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.jumrukovski.quotescompose.domain.model.Tag
import com.jumrukovski.quotescompose.ui.preview.parameter.TagPreviewParameter
import com.jumrukovski.quotescompose.ui.theme.PrimaryBackgroundColor
import com.jumrukovski.quotescompose.ui.theme.TertiaryColor
import com.jumrukovski.quotescompose.ui.theme.mediumTextStyle
import com.jumrukovski.quotescompose.ui.theme.spacing

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
                .padding(MaterialTheme.spacing.normal),
            text = tag.name,
            style = mediumTextStyle()
        )
    }
}

@Preview
@Composable
private fun TagCardPreview(
    @PreviewParameter(TagPreviewParameter::class) tag: Tag
) {
    TagCard(tag = tag, onItemClicked = {})
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun TagCardDarkPreview(
    @PreviewParameter(TagPreviewParameter::class) tag: Tag
) {
    TagCard(tag = tag, onItemClicked = {})
}
