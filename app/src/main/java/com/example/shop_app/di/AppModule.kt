package com.example.shop_app.di

import android.content.Context
import androidx.room.Room
import com.example.shop_app.data.client.ITEMS_DATABASE_NAME
import com.example.shop_app.data.client.dataStore.SettingsDataStore
import com.example.shop_app.data.client.dataStore.UserDataStore
import com.example.shop_app.data.client.room.ItemsDatabase
import com.example.shop_app.data.client.room.ItemsRepositoryImpl
import com.example.shop_app.data.remote.ITEMS_BASE_URL
import com.example.shop_app.data.remote.dto.ItemsService
import com.example.shop_app.domain.repositories.ItemsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideItemsApi(): ItemsService {
        return Retrofit.Builder()
            .baseUrl(ITEMS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ItemsService::class.java)
    }

    @Provides
    @Singleton
    fun provideItemsDatabase(@ApplicationContext context: Context): ItemsDatabase {
        return Room.databaseBuilder(
            context,
            ItemsDatabase::class.java,
            ITEMS_DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDataStore(@ApplicationContext context: Context): UserDataStore {
        return UserDataStore(context)
    }

    @Provides
    @Singleton
    fun provideSettingsDataStore(@ApplicationContext context: Context) : SettingsDataStore {
        return SettingsDataStore(context)
    }

    @Binds
    fun provideItemsRepository(repo: ItemsRepositoryImpl): ItemsRepository {
        return repo
    }

}