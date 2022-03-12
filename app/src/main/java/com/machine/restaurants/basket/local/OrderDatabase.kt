package com.machine.restaurants.basket.local
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Order::class], version = 1)
@TypeConverters(Converters::class)
abstract class OrderDatabase : RoomDatabase() {
    abstract fun orderDao(): OrderDao
}