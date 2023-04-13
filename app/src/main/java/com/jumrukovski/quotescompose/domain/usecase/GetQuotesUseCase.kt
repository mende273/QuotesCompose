package com.jumrukovski.quotescompose.domain.usecase

import com.jumrukovski.quotescompose.data.repository.RemoteRepository
import com.jumrukovski.quotescompose.domain.mapper.wrapQuotesAsResponseResult

class GetQuotesUseCase(private val remoteRepository: RemoteRepository) {

    suspend operator fun invoke() = remoteRepository.getQuotes().wrapQuotesAsResponseResult()
}