package com.jumrukovski.quotescompose.ui.preview.parameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.jumrukovski.quotescompose.domain.model.Quote

class ListOfQuotesPreviewParameter : PreviewParameterProvider<List<Quote>> {
    override val values: Sequence<List<Quote>> = sequenceOf(
        listOf(
            Quote(
                "1",
                "Everything comes to him who hustles while he waits.",
                "Thomas Edison"
            ),
            Quote(
                "2",
                "Change in all things is sweet.",
                "Aristotle"
            ),
            Quote(
                "3",
                "I never think of the future - it comes soon enough.",
                "Albert Einstein"
            ),
            Quote(
                "4",
                "As a cure for worrying, work is better than whisky.",
                "Thomas Edison"
            )
        )
    )
}
