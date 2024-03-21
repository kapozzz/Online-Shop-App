package com.example.shop_app.data.client.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.shop_app.domain.model.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemsDao {

    @Query("SELECT * FROM items")
    fun getAllItems(): Flow<List<Item>>

    @Query("SELECT * FROM items WHERE isLiked = 1")
    fun getLikedItems(): Flow<List<Item>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItems(list: List<Item>)

    @Update
    fun updateItem(item: Item)

}