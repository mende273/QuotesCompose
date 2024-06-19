package com.jumrukovski.quotescompose.di

import com.jumrukovski.quotescompose.data.db.LocalDB
import com.jumrukovski.quotescompose.data.network.ApiService
import com.jumrukovski.quotescompose.data.repository.LocalRepositoryImpl
import com.jumrukovski.quotescompose.data.repository.RemoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRemoteRepository(apiService: ApiService): RemoteRepositoryImpl {
        return RemoteRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideLocalRepository(localDB: LocalDB): LocalRepositoryImpl {
        return LocalRepositoryImpl(localDB)
    }
}
