package com.jumrukovski.quotescompose.ui.screen.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jumrukovski.quotescompose.ui.theme.PrimaryBackgroundColor

@Composable
fun CategoriesScreen(viewModel: CategoriesViewModel,
                     onNavigateToCategoryItems: (String) -> Unit) {

    LaunchedEffect(key1 = "items") {
        viewModel.getAllTags()
    }

    val tagItems by viewModel.items.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.PrimaryBackgroundColor)
    ) {
        LazyColumn() {
            items(tagItems) {
                Text(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable {
                        onNavigateToCategoryItems(it.name)
                    }, text = it.name, color = Color.Black
                )
            }
        }
    }
}