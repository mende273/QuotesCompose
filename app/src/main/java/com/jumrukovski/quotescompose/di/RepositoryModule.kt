package com.jumrukovski.quotescompose.di

import com.jumrukovski.quotescompose.data.repository.LocalRepositoryImpl
import com.jumrukovski.quotescompose.data.repository.RemoteRepositoryImpl
import com.jumrukovski.quotescompose.domain.repository.LocalRepository
import com.jumrukovski.quotescompose.domain.repository.RemoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    internal abstract fun bindLocalRepository(repository: LocalRepositoryImpl): LocalRepository

    @Binds
    @Singleton
    internal abstract fun bindRemoteRepository(repository: RemoteRepositoryImpl): RemoteRepository
}
