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
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "isLiked") val isLiked: Boolean,
    @ColumnInfo(name = "inBasket") val inBasket: Boolean
) {
    companion object {
        fun getEmptyItem(): Item {
            return Item(
                id = "empty",
                available = 0,
                description = "empty",
                feedback = Feedback(
                    count = 0,
                    rating = 0.0
                ),
                info = emptyList(),
                ingredients = "empty",
                price = Price(
                    discount = 0,
                    price = "empty",
                    priceWithDiscount = "empty",
                    unit = "empty"
                ),
                subtitle = "empty",
                tags = emptyList(),
                title = "empty",
                image = "empty",
                isLiked = false,
                inBasket = false
            )
        }
    }
}