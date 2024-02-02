package com.example.shop_app.domain.repositories

import com.example.shop_app.domain.model.User

interface UserRepository {

    suspend fun getUser(): User

}