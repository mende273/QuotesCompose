package com.jumrukovski.quotescompose.ui.feature.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.jumrukovski.quotescompose.R

enum class MainScreenMenuItem(@StringRes val titleTextId: Int, @DrawableRes val icon: Int) {
    SEARCH(R.string.action_search, R.drawable.baseline_search_24)
}