package com.example.shop_app.data.remote.util

import com.example.shop_app.data.remote.dto.ItemDto
import com.example.shop_app.data.remote.dto.ItemsResponse
import com.example.shop_app.domain.model.Item

fun ItemsResponse.toItems(): List<Item> = this.itemDtos.map { it.toItem() }

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
    isLiked = false
)

