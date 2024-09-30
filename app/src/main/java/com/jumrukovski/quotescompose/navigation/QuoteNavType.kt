package com.jumrukovski.quotescompose.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.jumrukovski.quotescompose.domain.model.Quote
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object QuoteNavType {

    val QuoteType = object : NavType<Quote>(
        isNullableAllowed = false
    ) {
        override fun get(bundle: Bundle, key: String): Quote? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): Quote {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: Quote): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: Quote) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }
}
