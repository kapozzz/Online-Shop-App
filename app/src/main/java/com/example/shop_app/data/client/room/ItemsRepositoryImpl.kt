package com.example.shop_app.data.client.room

import com.example.shop_app.domain.model.Item
import com.example.shop_app.domain.repositories.ItemsRepository
import javax.inject.Inject

class ItemsRepositoryImpl @Inject constructor(
    private val itemsDatabase: ItemsDatabase
): ItemsRepository {

    override suspend fun getItems(): List<Item> {
        return itemsDatabase.itemsDao().getAllItems()
    }

    override suspend fun getLikedItems(): List<Item> {
        return itemsDatabase.itemsDao().getLikedItems()
    }

    override suspend fun updateItem(item: Item) {
        itemsDatabase.itemsDao().updateItem(item = item)
    }

    override suspend fun addItems(items: List<Item>) {
        itemsDatabase.itemsDao().insertItems(items)
    }

}