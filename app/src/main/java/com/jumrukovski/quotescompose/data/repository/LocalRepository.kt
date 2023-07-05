package com.jumrukovski.quotescompose.data.repository

import com.jumrukovski.quotescompose.data.db.LocalDB
import com.jumrukovski.quotescompose.data.model.entity.FavouriteQuoteEntity
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class LocalRepository @Inject constructor(private val localDB: LocalDB) {

    fun getAllFavouriteQuotesAsync(): Flow<List<FavouriteQuoteEntity>> =
        localDB.quoteDao().getAllFavouriteQuotesAsync()

    fun getFavouriteQuoteAsync(id: String): Flow<FavouriteQuoteEntity?> =
        localDB.quoteDao().getFavouriteQuoteAsync(id)

    fun addFavouriteQuote(entity: FavouriteQuoteEntity) {
        localDB.quoteDao().addFavouriteQuote(entity)
    }

    fun removeFavouriteQuote(entity: FavouriteQuoteEntity) {
        localDB.quoteDao().deleteFavouriteQuote(entity)
    }
}
