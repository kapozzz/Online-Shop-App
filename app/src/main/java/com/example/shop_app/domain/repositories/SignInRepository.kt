package com.example.shop_app.domain.repositories

import com.example.shop_app.domain.model.User
import com.example.shop_app.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface SignInRepository {

    suspend fun saveUser(user: User)

    suspend fun isFirstStart(): Boolean

}