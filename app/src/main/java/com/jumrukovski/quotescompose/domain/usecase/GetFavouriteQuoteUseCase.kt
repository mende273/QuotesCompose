package com.jumrukovski.quotescompose.domain.usecase

import com.jumrukovski.quotescompose.data.repository.LocalRepository
import com.jumrukovski.quotescompose.domain.mapper.mapToQuote

class GetFavouriteQuoteUseCase(private val localRepository: LocalRepository) {

    operator fun invoke(id: String) = localRepository.getFavouriteQuoteAsync(id).mapToQuote()
}