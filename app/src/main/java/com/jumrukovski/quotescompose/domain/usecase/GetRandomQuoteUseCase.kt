package com.jumrukovski.quotescompose.domain.usecase

import com.jumrukovski.quotescompose.data.repository.RemoteRepository
import com.jumrukovski.quotescompose.domain.mapper.wrapQuoteAsResponseResult

class GetRandomQuoteUseCase(private val remoteRepository: RemoteRepository) {

    suspend operator fun invoke() = remoteRepository.getRandomQuote().wrapQuoteAsResponseResult()
}