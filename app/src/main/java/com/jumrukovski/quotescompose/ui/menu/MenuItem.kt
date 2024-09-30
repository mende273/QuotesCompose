package com.jumrukovski.quotescompose.ui.menu

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class MenuItem(
    @StringRes val titleTextId: Int,
    @DrawableRes val icon: Int
)
