package com.jumrukovski.quotescompose.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class MenuItem(
    @StringRes val titleTextId: Int,
    @DrawableRes val icon: Int,
    @DrawableRes val selectedIcon: Int,
    var isSelected: Boolean
)
