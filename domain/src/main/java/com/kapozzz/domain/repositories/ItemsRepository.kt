package com.kapozzz.domain.repositories

import com.kapozzz.domain.model.UiItem
import kotlinx.coroutines.flow.Flow

interface ItemsRepository {

    suspend fun getItems(): Flow<List<UiItem>>

    suspend fun getItemById(id: String): Flow<UiItem>

    suspend fun getLikedItems(): Flow<List<UiItem>>

    suspend fun getItemsInBasket(): Flow<List<UiItem>>

    suspend fun updateItem(item: UiItem)

    suspend fun addItems(items: List<UiItem>)

}