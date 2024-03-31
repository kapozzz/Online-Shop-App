package com.kapozzz.cart.di

import com.kapozzz.cart.navigation.CartApi
import com.kapozzz.cart.navigation.CartApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CartModule {

    @Provides
    fun provideCartApi(): CartApi {
        return CartApiImpl()
    }


}