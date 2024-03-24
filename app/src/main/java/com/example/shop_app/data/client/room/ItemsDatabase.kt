package com.example.shop_app.data.client.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.shop_app.domain.model.Item

@Database(
    entities = [Item::class],
    version = 6,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ItemsDatabase : RoomDatabase() {
    abstract fun itemsDao(): ItemsDao
}