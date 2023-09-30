package com.jumrukovski.quotescompose.di

import android.content.Context
import androidx.room.Room
import com.jumrukovski.quotescompose.data.db.LocalDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): LocalDB {
        return Room.databaseBuilder(context, LocalDB::class.java, "QuotesDB").build()
    }

    @Singleton
    @Provides
    fun provideYourDao(db: LocalDB) = db.quoteDao()
}
