package com.jumrukovski.quotescompose.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jumrukovski.quotescompose.ui.common.Screen
import com.jumrukovski.quotescompose.ui.theme.PrimaryBackgroundColor

@Composable
fun HomeScreen(navHostController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.PrimaryBackgroundColor)
    ) {
        HomeScreenContents(navHostController)
    }
}

@Composable
private fun HomeScreenContents(navHostController: NavHostController){
    val dumbItems = listOf("Category 1","Category 2","Category 3","Category 4")
    val dumbInnerItems = ArrayList<String>()

    var selectedCategoryName by remember {
        mutableStateOf(dumbItems[0])
    }

    for(i in 0..50){
        dumbInnerItems.add("$selectedCategoryName subItem $i")
    }
    Column {
        LazyRow{
            items(dumbItems){
                Box(modifier = Modifier.padding(16.dp).clickable {
                    selectedCategoryName = it
                }){
                    Text(text = it)
                }
            }
        }
        LazyColumn{
            items(dumbInnerItems){
                Box(modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .clickable {
                        navHostController.navigate(Screen.QuoteDetail.route)
                    }){
                    Text(text = it)
                }
            }
        }
    }
}