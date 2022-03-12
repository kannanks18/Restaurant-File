package com.machine.restaurants.basket.ui.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.machine.restaurants.basket.local.MenuItem
import com.machine.restaurants.basket.local.MenuList
import com.machine.restaurants.basket.local.Order
import com.machine.restaurants.basket.local.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val orderRepository: OrderRepository
) : ViewModel() {
    val orderList = orderRepository.getAllOrders()
    val totalPrice = orderRepository.getTotalPrice()
    val totalCount = orderRepository.getTotalCount()

    fun addToBasket(menuItem: Order?) {
        viewModelScope.launch {
            try {
                orderRepository.addItem(menuItem!!)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
    val reviewList = orderRepository.getAllOrderList()

}