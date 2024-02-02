package com.example.shop_app.data.client.dataStore

import com.example.shop_app.domain.model.User
import com.example.shop_app.domain.repositories.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataStore: UserDataStore
): UserRepository {

    override suspend fun getUser(): User {
        return userDataStore.getUser()
    }

}