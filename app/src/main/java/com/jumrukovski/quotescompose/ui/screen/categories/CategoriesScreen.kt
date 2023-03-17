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
import androidx.navigation.NavHostController
import com.jumrukovski.quotescompose.navigation.Screen
import com.jumrukovski.quotescompose.navigation.addCategoryNameAsRouteArgument
import com.jumrukovski.quotescompose.ui.theme.PrimaryBackgroundColor

@Composable
fun CategoriesScreen(navHostController: NavHostController, viewModel: CategoriesViewModel) {

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
                        navHostController.navigate(Screen.CategoryDetail.route.addCategoryNameAsRouteArgument(it.name)){
                           // popUpTo(navHostController.graph.findStartDestination().id)
                            launchSingleTop = true
                        }
                    }, text = it.name, color = Color.Black
                )
            }
        }
    }
}