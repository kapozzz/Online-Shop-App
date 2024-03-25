package com.kapozzz.entry.di

import com.kapozzz.entry.navigation.EntryApi
import com.kapozzz.entry.navigation.EntryApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object EntryModule {

    @Provides
    fun provideEntryApi(): EntryApi {
        return EntryApiImpl()
    }


}