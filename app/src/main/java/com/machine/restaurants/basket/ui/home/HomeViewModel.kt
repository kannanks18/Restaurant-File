package com.machine.restaurants.basket.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.machine.restaurants.basket.local.MenuList
import com.machine.restaurants.basket.local.Order
import com.machine.restaurants.basket.local.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val orderRepository: OrderRepository
) : ViewModel() {

    val orderList = orderRepository.getAllOrders()
    val totalItems = orderRepository.getCount()
    val totalPrice = orderRepository.getTotalPrice()

    private val _invoke: MutableStateFlow<Int> = MutableStateFlow(1)
    val invoke: StateFlow<Int> get() = _invoke
    fun addToMenu(menuList: MenuList) {
        viewModelScope.launch {
            try {
                for (i in 0 until menuList.menus.size) {
                    val count = 0;
                    val price = 0;
                    val order = Order(food = menuList.menus[i], count = count, total = price)
                    orderRepository.addItem(order)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }

        }.invokeOnCompletion {
            viewModelScope.launch {
                _invoke.emit(0)
            }
        }

    }
}