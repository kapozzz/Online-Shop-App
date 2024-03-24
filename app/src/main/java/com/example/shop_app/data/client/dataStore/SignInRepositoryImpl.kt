package com.example.shop_app.data.client.dataStore

import com.example.shop_app.domain.model.User
import com.example.shop_app.domain.repositories.SignInRepository
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(
    private val userDataStore: UserDataStore,
    private val settingsDataStore: SettingsDataStore
) : SignInRepository {

    override suspend fun saveUser(user: User) {
        userDataStore.saveUser(user = user)
        settingsDataStore.itStarted()
    }

    override suspend fun isFirstStart(): Boolean {
        return settingsDataStore.isFirstStart()
    }

}