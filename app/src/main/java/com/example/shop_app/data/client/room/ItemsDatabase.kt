package com.example.shop_app.data.client.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shop_app.domain.model.Item

@Database(entities = [Item::class], version = 1)
abstract class ItemsDatabase: RoomDatabase() {
    abstract fun itemsDao(): ItemsDao
}