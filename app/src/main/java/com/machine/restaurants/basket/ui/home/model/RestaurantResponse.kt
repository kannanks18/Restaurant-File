package com.machine.restaurants.basket.ui.home.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class RestaurantResponse(
    @Json(name = "restaurants")
    val restaurants: List<Restaurant>
): Serializable