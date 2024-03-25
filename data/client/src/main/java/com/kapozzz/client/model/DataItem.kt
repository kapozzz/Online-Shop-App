package com.kapozzz.client.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kapozzz.domain.model.Feedback
import com.kapozzz.domain.model.Info
import com.kapozzz.domain.model.Price
import com.kapozzz.domain.model.UiItem

@Entity(tableName = "items")
data class DataItem(
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
)

internal fun DataItem.toUI() = UiItem(
    id = this.id,
    available = this.available,
    description = this.description,
    feedback = this.feedback,
    info = this.info,
    ingredients = this.ingredients,
    price = this.price,
    subtitle = this.subtitle,
    tags = this.tags,
    title = this.title,
    image = this.image,
    isLiked = this.isLiked,
    inBasket = this.inBasket
)

internal fun UiItem.toDataItem() = DataItem(
    id = this.id,
    available = this.available,
    description = this.description,
    feedback = this.feedback,
    info = this.info,
    ingredients = this.ingredients,
    price = this.price,
    subtitle = this.subtitle,
    tags = this.tags,
    title = this.title,
    image = this.image,
    isLiked = this.isLiked,
    inBasket = this.inBasket
)
