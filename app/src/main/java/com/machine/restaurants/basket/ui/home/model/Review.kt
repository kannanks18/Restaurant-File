package com.machine.restaurants.basket.ui.home.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Review(
    @Json(name = "comments")
    val comments: String,
    @Json(name = "date")
    val date: String,
    @Json(name = "rname")
    val rname: String,
    @Json(name = "rating")
    val rating: Int
): Serializable