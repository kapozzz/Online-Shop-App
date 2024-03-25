package com.kapozzz.domain.repositories

import com.kapozzz.domain.model.User

interface UserRepository {
    suspend fun getUser(): User
}