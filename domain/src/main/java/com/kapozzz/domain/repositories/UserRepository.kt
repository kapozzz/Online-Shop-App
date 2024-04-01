package com.kapozzz.domain.repositories

import com.kapozzz.domain.model.User

interface UserRepository {

    suspend fun saveUser(user: User)

    suspend fun getUser(): User

    suspend fun exit()

    suspend fun isFirstStart(): Boolean

}