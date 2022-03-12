package com.machine.restaurants.basket.local

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class MenuList(
    @Json(name = "menus")
    val menus: List<MenuItem>
) : Parcelable