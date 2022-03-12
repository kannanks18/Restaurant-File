package com.machine.restaurants.basket.local


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
@JsonClass(generateAdapter = true)
data class Food(

    @Json(name = "categoryid")
    val categoryId: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "discount")
    val discount: String?,
    @Json(name = "image")
    val image: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "price")
    val price: String?,
    @Json(name = "productid")
    val productId: String?,
    @Json(name = "quantity")
    val quantity: String?,
    @Json(name = "rating")
    val rating: String?
) : Parcelable