package com.jumrukovski.quotescompose.domain.usecase

import com.jumrukovski.quotescompose.data.repository.LocalRepository
import com.jumrukovski.quotescompose.domain.mapper.mapToFavouriteQuoteEntity

class RemoveQuoteFromFavouritesDBUseCase(private val localRepository: LocalRepository) {

    operator fun invoke(id: String, content: String, author: String) {
        localRepository.removeFavouriteQuote(mapToFavouriteQuoteEntity(id, content, author))
    }
}
