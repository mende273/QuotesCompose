package com.jumrukovski.quotescompose.di

import com.jumrukovski.quotescompose.data.repository.LocalRepository
import com.jumrukovski.quotescompose.data.repository.RemoteRepository
import com.jumrukovski.quotescompose.domain.usecase.AddFavouriteQuoteToDBUseCase
import com.jumrukovski.quotescompose.domain.usecase.GetAllFavouriteQuotesUseCase
import com.jumrukovski.quotescompose.domain.usecase.GetAllTagsUseCase
import com.jumrukovski.quotescompose.domain.usecase.GetFavouriteQuoteUseCase
import com.jumrukovski.quotescompose.domain.usecase.GetQuotesForTagUseCase
import com.jumrukovski.quotescompose.domain.usecase.GetQuotesUseCase
import com.jumrukovski.quotescompose.domain.usecase.GetRandomQuoteUseCase
import com.jumrukovski.quotescompose.domain.usecase.RemoveQuoteFromFavouritesDBUseCase
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
    fun provideGetAllTagsUseCase(remoteRepository: RemoteRepository) =
        GetAllTagsUseCase(remoteRepository)

    @Provides
    @Singleton
    fun provideGetQuotesForTagUseCase(remoteRepository: RemoteRepository) =
        GetQuotesForTagUseCase(remoteRepository)

    @Provides
    @Singleton
    fun provideGetRandomQuoteUseCase(remoteRepository: RemoteRepository) =
        GetRandomQuoteUseCase(remoteRepository)

    @Provides
    @Singleton
    fun provideGetQuotesUseCase(remoteRepository: RemoteRepository) =
        GetQuotesUseCase(remoteRepository)

    @Provides
    @Singleton
    fun provideGetAllFavouriteQuotesUseCase(localRepository: LocalRepository) =
        GetAllFavouriteQuotesUseCase(localRepository)

    @Provides
    @Singleton
    fun provideGetFavouriteQuoteUseCase(localRepository: LocalRepository) =
        GetFavouriteQuoteUseCase(localRepository)

    @Provides
    @Singleton
    fun provideAddFavouriteQuoteToDBUseCase(localRepository: LocalRepository) =
        AddFavouriteQuoteToDBUseCase(localRepository)

    @Provides
    @Singleton
    fun provideRemoveQuoteFromFavouritesDBUseCase(localRepository: LocalRepository) =
        RemoveQuoteFromFavouritesDBUseCase(localRepository)
}
