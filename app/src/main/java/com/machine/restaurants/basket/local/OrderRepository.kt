package com.machine.restaurants.basket.local

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class OrderRepository @Inject constructor(
    private val orderDao: OrderDao
) {

    suspend fun addItem(order: Order) = orderDao.insert(order)

    suspend fun removeItem(order: Order) = orderDao.delete(order)

    suspend fun deleteAllOrders() = orderDao.deleteAllOrders()

    fun getAllOrders() = orderDao.getAllOrders()

    fun getAllOrderList() = orderDao.getAllOrders()

    fun getTotalPrice() = orderDao.getTotalPrice()

    fun getTotalCount() = orderDao.getTotalCount()

    fun getCount() = orderDao.getTotalItems()

    suspend fun insertAll(order: List<Order>) = orderDao.insertAll(order = order)

}