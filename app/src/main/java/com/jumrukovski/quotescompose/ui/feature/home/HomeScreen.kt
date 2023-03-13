package com.jumrukovski.quotescompose.ui.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jumrukovski.quotescompose.ui.theme.PrimaryBackgroundColor

@Composable
fun HomeScreen(navHostController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.PrimaryBackgroundColor)
    ) {
        MainScreen()
    }
}

@Composable
private fun MainScreen(){
    val dumbItems = listOf("item 1","item 2","item 3","item 4")
    Column {
        LazyRow{
            items(dumbItems){
                Box(modifier = Modifier.padding(16.dp)){
                    Text(text = it)
                }
            }
        }
    }
}