package com.jumrukovski.quotescompose.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jumrukovski.quotescompose.data.model.entity.FavouriteQuoteEntity

@Database(entities = [FavouriteQuoteEntity::class], version = 1)
abstract class LocalDB : RoomDatabase() {

    abstract fun quoteDao(): QuoteDao
}