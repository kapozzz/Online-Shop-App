package com.example.shop_app.data.remote.dto

import com.example.shop_app.domain.model.Feedback
import com.example.shop_app.domain.model.Info
import com.example.shop_app.domain.model.Price

data class ItemDto(
    val available: Int,
    val description: String,
    val feedback: Feedback,
    val id: String,
    val info: List<Info>,
    val ingredients: String,
    val price: Price,
    val subtitle: String,
    val tags: List<String>,
    val title: String
)