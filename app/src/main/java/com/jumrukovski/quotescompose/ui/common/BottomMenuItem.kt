package com.jumrukovski.quotescompose.ui.common

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.jumrukovski.quotescompose.R

enum class BottomMenuItem(@StringRes val titleTextId: Int, @DrawableRes val icon: Int) {
    HOME(R.string.bottom_navigation_menu_item_home, R.drawable.baseline_home_24),
    CATEGORIES(R.string.bottom_navigation_menu_item_categories, R.drawable.baseline_category_24),
    FAVOURITES(R.string.bottom_navigation_menu_item_favourites, R.drawable.baseline_favorite_24)
}