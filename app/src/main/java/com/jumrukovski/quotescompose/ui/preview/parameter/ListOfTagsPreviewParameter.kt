package com.jumrukovski.quotescompose.ui.preview.parameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.jumrukovski.quotescompose.domain.model.Tag

class ListOfTagsPreviewParameter : PreviewParameterProvider<List<Tag>> {
    override val values: Sequence<List<Tag>> = sequenceOf(
        listOf(
            Tag("1", "Age"),
            Tag("2", "Athletics"),
            Tag("3", "Business"),
            Tag("4", "Change"),
            Tag("5", "Character"),
            Tag("6", "Competition"),
            Tag("7", "Conservative"),
            Tag("8", "Courage"),
            Tag("9", "Creativity"),
            Tag("10", "Education"),
            Tag("11", "Ethics"),
            Tag("12", "Family")
        )
    )
}
