package com.example.shop_app.di

import android.content.Context
import androidx.room.Room
import com.example.shop_app.core.common.NetworkConnectivityObserver
import com.example.shop_app.data.client.ITEMS_DATABASE_NAME
import com.example.shop_app.data.client.dataStore.SettingsDataStore
import com.example.shop_app.data.client.dataStore.SignInRepositoryImpl
import com.example.shop_app.data.client.dataStore.UserDataStore
import com.example.shop_app.data.client.room.ItemsDatabase
import com.example.shop_app.data.client.room.ItemsRepositoryImpl
import com.example.shop_app.data.remote.ITEMS_BASE_URL
import com.example.shop_app.data.remote.ItemsService
import com.example.shop_app.domain.repositories.ItemsRepository
import com.example.shop_app.domain.repositories.SignInRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideItemsApi(): ItemsService {

        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(logger)
            .build()

        return Retrofit.Builder()
            .baseUrl(ITEMS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ItemsService::class.java)
    }

    @Provides
    @Singleton
    fun provideItemsDatabase(@ApplicationContext context: Context): ItemsDatabase {
        return Room
            .databaseBuilder(
                context,
                ItemsDatabase::class.java,
                ITEMS_DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDataStore(@ApplicationContext context: Context): UserDataStore {
        return UserDataStore(context)
    }

    @Provides
    @Singleton
    fun provideSettingsDataStore(@ApplicationContext context: Context): SettingsDataStore {
        return SettingsDataStore(context)
    }

    @Provides
    fun provideItemsRepository(repo: ItemsRepositoryImpl): ItemsRepository {
        return repo
    }

    @Provides
    fun provideSignInRepository(repo: SignInRepositoryImpl): SignInRepository {
        return repo
    }

    @Provides
    @Singleton
    fun provideConnectivityObserver(@ApplicationContext context: Context): NetworkConnectivityObserver {
        return NetworkConnectivityObserver(context)
    }

}