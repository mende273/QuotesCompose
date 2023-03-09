package com.jumrukovski.quotescompose

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

enum class MainScreenMenuItem(@StringRes val titleTextId: Int, @DrawableRes val icon: Int) {
    SEARCH(R.string.action_search, R.drawable.baseline_search_24)
}