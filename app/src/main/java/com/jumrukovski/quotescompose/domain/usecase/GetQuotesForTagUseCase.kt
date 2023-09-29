package com.jumrukovski.quotescompose.domain.usecase

import com.jumrukovski.quotescompose.data.repository.RemoteRepository
import com.jumrukovski.quotescompose.domain.mapper.wrapQuotesAsResponseResult

class GetQuotesForTagUseCase(private val remoteRepository: RemoteRepository) {

    suspend operator fun invoke(tag: String) =
        remoteRepository.getQuotesForTag(tag).wrapQuotesAsResponseResult()
}
