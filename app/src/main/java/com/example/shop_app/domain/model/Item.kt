package com.example.shop_app.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class Item(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "available") val available: Int,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "feedback") val feedback: Feedback,
    @ColumnInfo(name = "info") val info: List<Info>,
    @ColumnInfo(name = "ingredients") val ingredients: String,
    @ColumnInfo(name = "price") val price: Price,
    @ColumnInfo(name = "subtitle") val subtitle: String,
    @ColumnInfo(name = "tags") val tags: List<String>,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "isLiked") val isLiked: Boolean
)