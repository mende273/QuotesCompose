package com.jumrukovski.quotescompose.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jumrukovski.quotescompose.data.model.entity.QuoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {

    @Query("SELECT * FROM QuoteEntity")
    fun getAllFavouriteQuotes(): Flow<List<QuoteEntity>>

    @Query("SELECT * FROM QuoteEntity WHERE id=:id LIMIT 1")
    fun getFavouriteQuote(id: Int): Flow<QuoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavouriteQuote(entity: QuoteEntity)

    @Delete
    fun deleteFavouriteQuote(entity: QuoteEntity)
}
