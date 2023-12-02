package com.jumrukovski.quotescompose.ui.preview.parameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.jumrukovski.quotescompose.domain.model.Quote

class QuotePreviewParameter : PreviewParameterProvider<Quote> {
    override val values: Sequence<Quote> = sequenceOf(
        Quote(
            "1",
            "Everything comes to him who hustles while he waits.",
            "Thomas Edison"
        )
    )
}
