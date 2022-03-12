package com.machine.restaurants.basket.ui.home.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Latlng(
    @Json(name = "lat")
    val lat: Double,
    @Json(name = "lng")
    val lng: Double
): Serializable