package com.kapozzz.di

import com.kapozzz.RefreshDataRepositoryImpl
import com.kapozzz.domain.repositories.RefreshDataRepository
import com.kapozzz.remote.ItemsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

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
            .baseUrl("https://run.mocky.io/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ItemsService::class.java)
    }

    @Provides
    @Singleton
    fun provideRefreshDataRepository(repo: RefreshDataRepositoryImpl): RefreshDataRepository {
        return repo
    }

}