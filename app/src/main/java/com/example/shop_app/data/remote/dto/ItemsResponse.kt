package com.example.shop_app.data.remote.dto

import com.example.shop_app.domain.model.Item

data class ItemsResponse(
    val items: List<ItemDto>
)

fun ItemsResponse.toItems(): List<Item> = this.items.map { it.toItem() }