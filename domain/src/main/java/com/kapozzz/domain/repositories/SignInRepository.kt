package com.kapozzz.domain.repositories

import com.kapozzz.domain.model.User

interface SignInRepository {

    suspend fun saveUser(user: User)

    suspend fun isFirstStart(): Boolean

}