package com.example.shop_app.domain.model

import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("discount") val discount: Int,
    @SerializedName("price") val price: String,
    @SerializedName("priceWithDiscount") val priceWithDiscount: String,
    @SerializedName("unit") val unit: String
)