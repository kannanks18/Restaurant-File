package com.machine.restaurants.basket.local

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
@Keep
@Parcelize
@JsonClass(generateAdapter = true)
data class MenuItem(
    @Json(name = "D")
    val D: String,
    @Json(name = "N")
    val N: String,
    @Json(name = "amount")
    val amount: String,
    @Json(name = "desc")
    val desc: String,
    @Json(name = "menuid")
    val menuid: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "rating")
    val rating: String,
    var quantity: Int = 0
) : Parcelable