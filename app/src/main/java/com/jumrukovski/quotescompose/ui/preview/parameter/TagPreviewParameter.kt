package com.jumrukovski.quotescompose.ui.preview.parameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.jumrukovski.quotescompose.domain.model.Tag

class TagPreviewParameter : PreviewParameterProvider<Tag> {
    override val values: Sequence<Tag> = sequenceOf(
        Tag("1", "Character")
    )
}
