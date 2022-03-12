package com.machine.restaurants.basket.local
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(order: Order)

    @Delete
    suspend fun delete(order: Order)

    @Update()
    suspend fun update(order: Order)


    @Query("SELECT * FROM order_table")
    fun getAllOrders():Flow<List<Order>>


    @Query("SELECT SUM(total) FROM order_table")
    fun getTotalPrice():Flow<Int>
    @Query("SELECT SUM(count) FROM order_table")
    fun getTotalCount():Flow<Int>

    @Query("DELETE FROM order_table")
    suspend fun deleteAllOrders()

    @Query("SELECT COUNT(*) FROM order_table")
    fun getTotalItems():Flow<Int>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(order: List<Order>)

    @Query("SELECT * FROM order_table WHERE food>0")
    fun getAllOrderList():Flow<List<Order>>

}