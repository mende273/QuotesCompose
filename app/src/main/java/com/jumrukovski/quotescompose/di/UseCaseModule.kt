package com.jumrukovski.quotescompose.di

import com.jumrukovski.quotescompose.data.repository.RemoteRepository
import com.jumrukovski.quotescompose.domain.usecase.GetAllTagsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetAllTagsUseCase(remoteRepository: RemoteRepository): GetAllTagsUseCase {
        return GetAllTagsUseCase(remoteRepository)
    }
}