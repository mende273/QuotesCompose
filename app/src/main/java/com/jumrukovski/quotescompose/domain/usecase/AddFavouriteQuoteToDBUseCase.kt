package com.jumrukovski.quotescompose.domain.usecase

import com.jumrukovski.quotescompose.data.repository.LocalRepository
import com.jumrukovski.quotescompose.domain.mapper.mapToFavouriteQuoteEntity

class AddFavouriteQuoteToDBUseCase(private val localRepository: LocalRepository) {

    operator fun invoke(id:String,content:String,author:String) {
        localRepository.addFavouriteQuote(mapToFavouriteQuoteEntity(id, content, author))
    }
}