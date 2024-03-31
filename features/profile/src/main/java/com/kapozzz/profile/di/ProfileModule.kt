package com.kapozzz.profile.di

import com.kapozzz.profile.navigation.ProfileApiImpl
import com.kapozzz.profile.navigation.ProfileApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ProfileModule {

    @Provides
    fun provideProfileApi(): ProfileApi {
        return ProfileApiImpl()
    }


}