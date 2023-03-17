package com.jumrukovski.quotescompose.ui.screen.categories.details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jumrukovski.quotescompose.navigation.Screen
import com.jumrukovski.quotescompose.ui.theme.PrimaryBackgroundColor

@Composable
fun CategoryItemsScreen(navHostController: NavHostController, viewModel: CategoryItemsViewModel,categoryName:String) {
    LaunchedEffect(key1 = Screen.CategoryDetail.CATEGORY_NAME_ARGUMENT){
        viewModel.getQuotesForTag(categoryName)
    }

    val categoryItems by viewModel.items.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.PrimaryBackgroundColor)
            .padding(16.dp)
    ) {
        LazyColumn(){
            items(categoryItems){
                Box(modifier = Modifier.fillMaxSize().clickable {
                    navHostController.navigate(Screen.QuoteDetail.route)
                }){
                    Column {
                        Text(text = it.content)
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }
        }
    }
}