package com.example.shop_app.domain.repositories

import com.example.shop_app.domain.model.Item

interface ItemsRepository {

    suspend fun getItems(): List<Item>

    suspend fun getLikedItems(): List<Item>

    suspend fun updateItem(item: Item)

    suspend fun addItems(items: List<Item>)

}