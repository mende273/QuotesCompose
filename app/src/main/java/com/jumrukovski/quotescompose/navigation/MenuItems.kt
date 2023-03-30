package com.jumrukovski.quotescompose.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.jumrukovski.quotescompose.R

interface MenuItem

enum class RandomQuoteMenuItem(@StringRes val titleTextId: Int, @DrawableRes val icon: Int) :
    MenuItem {
    RANDOM(R.string.action_random, R.drawable.baseline_random)
}