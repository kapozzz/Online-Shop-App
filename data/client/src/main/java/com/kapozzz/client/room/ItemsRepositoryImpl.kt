package com.kapozzz.client.room

import com.kapozzz.client.model.toDataItem
import com.kapozzz.client.model.toUI
import com.kapozzz.domain.model.UiItem
import com.kapozzz.domain.repositories.ItemsRepository
import kotlinx.coroutines.flow.Flow


import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ItemsRepositoryImpl @Inject constructor(
    private val itemsDatabase: ItemsDatabase
) : ItemsRepository {

    override suspend fun getItems(): Flow<List<UiItem>> {
        return itemsDatabase
            .itemsDao()
            .getAllItems()
            .map { items ->
                items.map { item ->
                    item.toUI()
                }
            }
    }

    override suspend fun getItemById(id: String): Flow<UiItem> {
        return itemsDatabase.itemsDao().getItemById(id).map { item ->
            item.toUI()
        }
    }

    override suspend fun getLikedItems(): Flow<List<UiItem>> {
        return itemsDatabase.itemsDao().getLikedItems().map { items ->
            items.map { item ->
                item.toUI()
            }
        }
    }

    override suspend fun getLikedItemsCount(): Flow<Int> {
        return itemsDatabase.itemsDao().getLikedItemsCount()
    }

    override suspend fun getItemsInBasket(): Flow<List<UiItem>> {
        return itemsDatabase.itemsDao().getItemsInBasket().map { items ->
            items.map { item ->
                item.toUI()
            }
        }
    }

    override suspend fun updateItem(item: UiItem) {
        itemsDatabase.itemsDao().updateItem(item = item.toDataItem())
    }

    override suspend fun addItems(items: List<UiItem>) {
        itemsDatabase.itemsDao().insertItems(items.map { item ->
            item.toDataItem()
        })
    }

}