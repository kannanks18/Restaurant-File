package com.machine.restaurants.basket.local
import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
@Keep
@Parcelize
@Entity(tableName = "order_table")
data class Order(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val food: MenuItem,
    var count:Int=0,
    var total:Int =0
) : Parcelable
