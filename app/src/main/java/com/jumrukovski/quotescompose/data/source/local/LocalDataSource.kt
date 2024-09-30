package com.jumrukovski.quotescompose.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jumrukovski.quotescompose.data.model.entity.QuoteEntity

@Database(entities = [QuoteEntity::class], version = 1)
abstract class LocalDataSource : RoomDatabase() {

    abstract fun quoteDao(): QuoteDao
}
