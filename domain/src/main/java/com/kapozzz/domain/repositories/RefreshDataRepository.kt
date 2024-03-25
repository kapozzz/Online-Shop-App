package com.kapozzz.domain.repositories

import com.example.shop_app.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface RefreshDataRepository {

    fun refresh(): Flow<Resource<Unit>>

}