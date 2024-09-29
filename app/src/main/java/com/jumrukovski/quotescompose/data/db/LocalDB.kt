package com.jumrukovski.quotescompose.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jumrukovski.quotescompose.data.model.entity.QuoteEntity

@Database(entities = [QuoteEntity::class], version = 1)
abstract class LocalDB : RoomDatabase() {

    abstract fun quoteDao(): QuoteDao
}
