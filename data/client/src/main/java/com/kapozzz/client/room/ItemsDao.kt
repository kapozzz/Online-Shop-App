package com.kapozzz.client.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.kapozzz.client.model.DataItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemsDao {
    @Query("SELECT * FROM items")
    fun getAllItems(): Flow<List<DataItem>>

    @Query("SELECT * FROM items WHERE id=:itemId ")
    fun getItemById(itemId: String): Flow<DataItem>

    @Query("SELECT * FROM items WHERE isLiked = 1")
    fun getLikedItems(): Flow<List<DataItem>>

    @Query("SELECT COUNT(*) FROM items WHERE isLiked = 1")
    fun getLikedItemsCount(): Flow<Int>

    @Query("SELECT * FROM items WHERE inBasket = 1")
    fun getItemsInBasket(): Flow<List<DataItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItems(list: List<DataItem>)

    @Update
    fun updateItem(item: DataItem)

}