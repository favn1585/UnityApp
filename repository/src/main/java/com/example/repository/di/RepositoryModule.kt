package com.example.repository.di

import com.example.domain.repository.ActionsRepository
import com.example.repository.repository.ActionsRepositoryImpl
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
    abstract fun bindActionsRepository(repository: ActionsRepositoryImpl): ActionsRepository
}