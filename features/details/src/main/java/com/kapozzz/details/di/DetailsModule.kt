package com.kapozzz.details.di

import com.kapozzz.details.navigation.DetailsApi
import com.kapozzz.details.navigation.DetailsApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DetailsModule {

    @Provides
    fun provideDetailsApi(): DetailsApi {
        return DetailsApiImpl()
    }


}