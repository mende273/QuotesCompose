package com.jumrukovski.quotescompose.di

import com.jumrukovski.quotescompose.data.db.LocalDB
import com.jumrukovski.quotescompose.data.network.ApiService
import com.jumrukovski.quotescompose.data.repository.LocalRepository
import com.jumrukovski.quotescompose.data.repository.Repository
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
    fun provideRepository(apiService: ApiService): Repository {
        return Repository(apiService)
    }

    @Provides
    @Singleton
    fun provideLocalRepository(localDB: LocalDB): LocalRepository {
        return LocalRepository(localDB)
    }
}