package com.kapozzz.list.di

import com.kapozzz.list.navigation.ListApi
import com.kapozzz.list.navigation.ListApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ListModule {

    @Provides
    fun provideListApi(): ListApi {
        return ListApiImpl()
    }

}