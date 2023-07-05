package com.jumrukovski.quotescompose.domain.usecase

import com.jumrukovski.quotescompose.data.repository.RemoteRepository
import com.jumrukovski.quotescompose.domain.mapper.wrapTagsAsResponseResult

class GetAllTagsUseCase(private val remoteRepository: RemoteRepository) {

    suspend operator fun invoke() = remoteRepository.getAllTags().wrapTagsAsResponseResult()
}
