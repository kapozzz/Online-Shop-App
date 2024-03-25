package com.kapozzz.remote.dto

import com.kapozzz.domain.model.UiItem

data class ItemsResponse(
    val items: List<ItemDto>
)

internal fun ItemsResponse.toItems(): List<UiItem> = this.items.map { it.toItem() }