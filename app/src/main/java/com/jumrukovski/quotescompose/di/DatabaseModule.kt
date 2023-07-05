package com.jumrukovski.quotescompose.di

import android.content.Context
import androidx.room.Room
import com.jumrukovski.quotescompose.data.db.LocalDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Named("dbName")
    @Singleton
    @Provides
    fun provideDatabaseName(): String = "QuotesDB"

    @Singleton
    @Provides
    fun provideDatabase(context: Context, @Named("dbName") databaseName: String): LocalDB {
        return Room.databaseBuilder(context, LocalDB::class.java, databaseName).build()
    }

    @Singleton
    @Provides
    fun provideYourDao(db: LocalDB) = db.quoteDao()
}
