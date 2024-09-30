package com.jumrukovski.quotescompose.di

import com.jumrukovski.quotescompose.data.repository.local.LocalRepositoryImpl
import com.jumrukovski.quotescompose.data.repository.remote.RemoteRepositoryImpl
import com.jumrukovski.quotescompose.domain.repository.local.LocalRepository
import com.jumrukovski.quotescompose.domain.repository.remote.RemoteRepository
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
