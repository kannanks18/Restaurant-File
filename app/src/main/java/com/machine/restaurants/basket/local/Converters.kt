package com.machine.restaurants.basket.local

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

 class Converters {
    private val moshi: Moshi = Moshi.Builder().build()

    private val jsonAdapter: JsonAdapter<MenuItem> = moshi.adapter(MenuItem::class.java)

    @TypeConverter
    fun fromString(value: String): MenuItem {
        return jsonAdapter.fromJson(value)!!
    }

    @TypeConverter
    fun fromInfoType(type: MenuItem): String {
        return jsonAdapter.toJson(type)
    }
}