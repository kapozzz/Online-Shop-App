package com.kapozzz.client.room

import androidx.room.TypeConverter
import com.kapozzz.domain.model.Feedback
import com.kapozzz.domain.model.Info
import com.kapozzz.domain.model.Price
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kapozzz.client.model.DataItem

class Converters {

    @TypeConverter
    fun fromItem(item: DataItem): String = Gson().toJson(item)

    @TypeConverter
    fun toItem(jsonItem: String): DataItem = Gson().fromJson(jsonItem, DataItem::class.java)

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