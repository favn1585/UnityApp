package com.example.network.di

import com.example.domain.repository.ActionsRepository
import com.example.network.repository.ActionsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkRepositoryBindings {

    @Provides
    abstract fun bindRemoteConfigSource(repository: ActionsRepositoryImpl): ActionsRepository
}