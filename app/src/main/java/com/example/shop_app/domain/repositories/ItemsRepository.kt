package com.example.shop_app.domain.repositories

import com.example.shop_app.domain.model.Item
import kotlinx.coroutines.flow.Flow

interface ItemsRepository {

    suspend fun getItems(): Flow<List<Item>>

    suspend fun getLikedItems(): Flow<List<Item>>

    suspend fun updateItem(item: Item)

    suspend fun addItems(items: List<Item>)

}