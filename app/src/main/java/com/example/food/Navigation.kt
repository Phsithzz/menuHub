package com.example.food

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.food.screens.AuthScreen
import com.example.food.screens.CartScreen
import com.example.food.screens.FoodScreen
import com.example.food.screens.HomeScreen
import com.example.food.viewModels.CartViewModel
import com.example.food.viewModels.HomeViewModel

@Composable
fun Navigation(modifier: Modifier) {
    val navController = rememberNavController()
    val cartViewModel: CartViewModel = viewModel()
    val homeViewModel: HomeViewModel = viewModel()
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("main", Context.MODE_PRIVATE)
    val userLoggedIn = sharedPreferences.getBoolean("loggedIn", false)
    NavHost(
        navController = navController,
        startDestination = if (userLoggedIn) "home" else "auth",
        modifier = modifier
    ) {
        composable("auth") {
            AuthScreen(navController = navController)
        }
        composable("home") {
            HomeScreen(navController = navController, homeViewModel = homeViewModel, cartViewModel)
        }
        composable(
            route = "food/{foodId}",
            arguments = listOf(navArgument("foodId") { type = NavType.IntType })
        ) { backStackEntry ->
            val foodId = backStackEntry.arguments?.getInt("foodId")
            FoodScreen(
                navController = navController,
                foodId = foodId,
                cartViewModel = cartViewModel
            )
        }
        composable("cart") {
            CartScreen(navController, cartViewModel)
        }
    }


}
