package com.example.local.di

import android.content.Context
import androidx.room.Room
import com.example.local.ActionsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DBModule {

    @Provides
    @Singleton
    fun provideOrganisationDao(
        @ApplicationContext context: Context
    ): ActionsDatabase {
        return Room.databaseBuilder(context, ActionsDatabase::class.java, "actions_database.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}