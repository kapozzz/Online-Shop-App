package com.kapozzz.client.dataStore

import com.kapozzz.domain.model.User
import com.kapozzz.domain.repositories.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataStore: UserDataStore,
    private val settingsDataStore: SettingsDataStore
) : UserRepository {

    override suspend fun saveUser(user: User) {
        userDataStore.saveUser(user = user)
        settingsDataStore.itStarted()
    }

    override suspend fun getUser(): User {
        return userDataStore.getUser()
    }

    override suspend fun exit() {
        userDataStore.deleteUserInfo()
        settingsDataStore.exit()
    }

    override suspend fun isFirstStart(): Boolean {
        return settingsDataStore.isFirstStart()
    }

}