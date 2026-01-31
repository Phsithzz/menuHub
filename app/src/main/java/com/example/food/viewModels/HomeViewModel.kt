package com.example.food.viewModels

import androidx.lifecycle.ViewModel
import com.example.food.screens.foods
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {
    private val _foodList = MutableStateFlow(foods)
    val foodList = _foodList.asStateFlow()

    fun toggleLike(foodId: Int) {
        _foodList.update { currentList ->
            currentList.map { food ->
                if (food.id == foodId) {
                    food.copy(liked = !food.liked)
                } else {
                    food
                }
            }
        }
    }
}