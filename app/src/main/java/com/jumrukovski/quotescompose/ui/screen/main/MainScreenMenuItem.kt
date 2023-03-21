package com.jumrukovski.quotescompose.ui.screen.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.jumrukovski.quotescompose.R

enum class MainScreenMenuItem(@StringRes val titleTextId: Int, @DrawableRes val icon: Int) {
    RANDOM(R.string.action_random, R.drawable.baseline_random)
}