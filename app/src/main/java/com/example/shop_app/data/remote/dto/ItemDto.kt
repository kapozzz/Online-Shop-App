package com.example.shop_app.data.remote.dto

import com.example.shop_app.domain.model.Feedback
import com.example.shop_app.domain.model.Info
import com.example.shop_app.domain.model.Item
import com.example.shop_app.domain.model.Price
import com.google.gson.annotations.SerializedName

data class ItemDto(
    @SerializedName("available") val available: Int,
    @SerializedName("description") val description: String,
    @SerializedName("feedback") val feedback: Feedback,
    @SerializedName("id") val id: String,
    @SerializedName("info") val info: List<Info>,
    @SerializedName("ingredients") val ingredients: String,
    @SerializedName("price") val price: Price,
    @SerializedName("subtitle") val subtitle: String,
    @SerializedName("tags") val tags: List<String>,
    @SerializedName("title") val title: String,
    @SerializedName("image") val image: String
)

fun ItemDto.toItem(): Item = Item(
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
    isLiked = false,
    image = image
)