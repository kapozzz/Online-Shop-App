package com.kapozzz.client.dataStore

import com.kapozzz.domain.model.User
import com.kapozzz.domain.repositories.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataStore: UserDataStore
): UserRepository {

    override suspend fun getUser(): User {
        return userDataStore.getUser()
    }

}