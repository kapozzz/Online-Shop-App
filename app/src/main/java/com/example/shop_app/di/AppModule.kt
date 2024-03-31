package com.example.shop_app.di

import com.example.shop_app.navigation.NavigationProvider
import com.kapozzz.cart.navigation.CartApi
import com.kapozzz.details.navigation.DetailsApi
import com.kapozzz.entry.navigation.EntryApi
import com.kapozzz.profile.navigation.ProfileApi
import com.kapozzz.list.navigation.ListApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideNavigationProvider(
        entryApi: EntryApi,
        listApi: ListApi,
        detailsApi: DetailsApi,
        profileApi: ProfileApi,
        cartApi: CartApi
    ): NavigationProvider {
        return NavigationProvider(
            entry = entryApi,
            list = listApi,
            details = detailsApi,
            profile = profileApi,
            cart = cartApi
        )
    }

}