package com.kapozzz.di

import android.content.Context
import androidx.room.Room
import com.kapozzz.client.dataStore.SettingsDataStore
import com.kapozzz.client.dataStore.SignInRepositoryImpl
import com.kapozzz.client.dataStore.UserDataStore
import com.kapozzz.client.room.ItemsDatabase
import com.kapozzz.client.room.ItemsRepositoryImpl
import com.kapozzz.domain.repositories.ItemsRepository
import com.kapozzz.domain.repositories.SignInRepository
import com.kapozzz.util.ITEMS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ClientModule {

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

}