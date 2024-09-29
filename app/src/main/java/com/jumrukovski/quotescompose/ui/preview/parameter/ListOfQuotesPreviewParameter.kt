package com.jumrukovski.quotescompose.ui.preview.parameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.jumrukovski.quotescompose.domain.model.Quote

class ListOfQuotesPreviewParameter : PreviewParameterProvider<List<Quote>> {
    override val values: Sequence<List<Quote>> = sequenceOf(
        listOf(
            Quote(
                id = 1,
                content = "Everything comes to him who hustles while he waits.",
                author = "Thomas Edison"
            ),
            Quote(
                id = 2,
                content = "Change in all things is sweet.",
                author = "Aristotle"
            ),
            Quote(
                id = 3,
                content = "I never think of the future - it comes soon enough.",
                author = "Albert Einstein"
            ),
            Quote(
                id = 4,
                content = "As a cure for worrying, work is better than whisky.",
                author = "Thomas Edison"
            )
        )
    )
}
