package com.example.shop_app.data.client.room

import androidx.room.TypeConverter
import com.example.shop_app.domain.model.Feedback
import com.example.shop_app.domain.model.Info
import com.example.shop_app.domain.model.Item
import com.example.shop_app.domain.model.Price
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromItem(item: Item): String = Gson().toJson(item)

    @TypeConverter
    fun toItem(jsonItem: String): Item = Gson().fromJson(jsonItem, Item::class.java)

    @TypeConverter
    fun fromFeedback(feedback: Feedback): String = Gson().toJson(feedback)

    @TypeConverter
    fun toFeedback(jsonFeedback: String): Feedback = Gson().fromJson(jsonFeedback, Feedback::class.java)

    @TypeConverter
    fun fromInfo(info: List<Info>): String = Gson().toJson(info)

    @TypeConverter
    fun toInfo(jsonInfo: String): List<Info> {
        val type = object : TypeToken<List<Info>>() {}.type
        return Gson().fromJson(jsonInfo, type)
    }

    @TypeConverter
    fun fromPrice(price: Price): String = Gson().toJson(price)

    @TypeConverter
    fun toPrice(jsonPrice: String): Price = Gson().fromJson(jsonPrice, Price::class.java)

    @TypeConverter
    fun fromTags(tags: List<String>): String = Gson().toJson(tags)

    @TypeConverter
    fun toTags(jsonTags: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(jsonTags, type)
    }
}