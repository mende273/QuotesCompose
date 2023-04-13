package com.jumrukovski.quotescompose.domain.usecase

import com.jumrukovski.quotescompose.data.repository.LocalRepository
import com.jumrukovski.quotescompose.domain.mapper.mapToQuotes

class GetAllFavouriteQuotesUseCase(private val localRepository: LocalRepository) {

    operator fun invoke() = localRepository.getAllFavouriteQuotesAsync().mapToQuotes()
}