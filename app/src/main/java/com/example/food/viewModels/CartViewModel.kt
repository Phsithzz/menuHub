package com.example.food.viewModels

import androidx.lifecycle.ViewModel
import com.example.food.screens.Food
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CartViewModel : ViewModel() {

    private val _cartItems = MutableStateFlow<List<Food>>(emptyList())
    val cartItems = _cartItems.asStateFlow()

    fun addToCart(food: Food) {
        _cartItems.update { currentList ->
            currentList + food
        }

    }

    fun removeFood(food: Food) {
        _cartItems.update { currentList ->
            currentList - food
        }

    }

    fun clearCart() {
        _cartItems.value = emptyList()
    }

    fun getTotalPrice(): Int {
        return _cartItems.value.sumOf {
            it.price
        }
    }

}