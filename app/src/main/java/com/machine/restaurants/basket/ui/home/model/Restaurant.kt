package com.machine.restaurants.basket.ui.home.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Restaurant(
    @Json(name = "address")
    val address: String,
    @Json(name = "contact")
    val contact: String,
    @Json(name = "cuisine_type")
    val cuisine_type: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "latlng")
    val latlng: Latlng,
    @Json(name = "name")
    val name: String,
    @Json(name = "operating_hours")
    val operating_hours: String,
    @Json(name = "photograph")
    val photograph: String,
    @Json(name = "rating")
    val rating: String,
    @Json(name = "reviews")
    val reviews: List<Review>
) : Serializable