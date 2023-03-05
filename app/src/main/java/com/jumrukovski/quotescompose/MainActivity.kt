package com.jumrukovski.quotescompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jumrukovski.quotescompose.ui.theme.BlackColor
import com.jumrukovski.quotescompose.ui.theme.PrimaryBackgroundColor
import com.jumrukovski.quotescompose.ui.theme.PrimaryTextColor
import com.jumrukovski.quotescompose.ui.theme.QuotesComposeTheme

class MainActivity : ComponentActivity() {

    @Composable
    private fun Toolbar() {
        SmallTopAppBar(
            modifier = Modifier.background(MaterialTheme.colorScheme.PrimaryBackgroundColor),
            title = {
                Box(modifier = Modifier
                    .background(BlackColor)
                    .padding(all = 0.dp)) {
                    Text(
                        stringResource(R.string.app_name),
                        modifier = Modifier.background(MaterialTheme.colorScheme.PrimaryBackgroundColor),
                    )
                }
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.PrimaryBackgroundColor,
                scrolledContainerColor = MaterialTheme.colorScheme.PrimaryBackgroundColor,
                navigationIconContentColor = MaterialTheme.colorScheme.PrimaryTextColor,
                titleContentColor = MaterialTheme.colorScheme.PrimaryTextColor,
                actionIconContentColor = MaterialTheme.colorScheme.PrimaryTextColor
            )
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuotesComposeTheme {
                Scaffold(
                    topBar = { Toolbar() },
                    content = { padding ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(padding)
                                .background(MaterialTheme.colorScheme.PrimaryBackgroundColor)
                        ) {
                            //todo
                        }
                    })
            }
        }
    }
}