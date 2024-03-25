package com.kapozzz.client.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kapozzz.client.model.DataItem

@Database(
    entities = [DataItem::class],
    version = 8,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ItemsDatabase : RoomDatabase() {
    abstract fun itemsDao(): ItemsDao
}