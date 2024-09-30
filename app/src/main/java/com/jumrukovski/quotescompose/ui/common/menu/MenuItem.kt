package com.jumrukovski.quotescompose.ui.common.menu

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class MenuItem(
    @StringRes val titleTextId: Int,
    @DrawableRes val icon: Int
)
