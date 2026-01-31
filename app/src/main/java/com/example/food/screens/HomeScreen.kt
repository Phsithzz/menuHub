package com.example.food.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.collectAsState // สำคัญ
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue // สำคัญ
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.food.R
import com.example.food.components.TabLayout
import com.example.food.viewModels.CartViewModel
import com.example.food.viewModels.HomeViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController


data class Food(
    val id: Int,
    val name: String,
    @DrawableRes val image: Int,
    val type: FoodType,
    val liked: Boolean = false,
    val price: Int
)

enum class FoodType {
    Meal, Side, Snack
}

val foods = listOf(
    Food(1, "Meal 1", R.drawable.meal_1, FoodType.Meal, price = 50),
    Food(2, "Meal 2", R.drawable.meal_2, FoodType.Meal, price = 50),
    Food(3, "Meal 3", R.drawable.meal_3, FoodType.Meal, price = 50),
    Food(4, "Meal 4", R.drawable.meal_4, FoodType.Meal, price = 50),
    Food(5, "Meal 5", R.drawable.meal_5, FoodType.Meal, price = 50),
    Food(6, "Meal 6", R.drawable.meal_6, FoodType.Meal, price = 50),
    Food(7, "Side 1", R.drawable.sides_1, FoodType.Side, price = 40),
    Food(8, "Side 2", R.drawable.sides_2, FoodType.Side, price = 40),
    Food(9, "Side 3", R.drawable.sides_3, FoodType.Side, price = 40),
    Food(10, "Side 4", R.drawable.sides_4, FoodType.Side, price = 40),
    Food(11, "Side 5", R.drawable.sides_5, FoodType.Side, price = 40),
    Food(12, "Side 6", R.drawable.sides_6, FoodType.Side, price = 40),
    Food(13, "Snack 1", R.drawable.snacks_1, FoodType.Snack, price = 10),
    Food(14, "Snack 2", R.drawable.snacks_2, FoodType.Snack, price = 40),
    Food(15, "Snack 3", R.drawable.snacks_3, FoodType.Snack, price = 10),
    Food(16, "Snack 4", R.drawable.snacks_4, FoodType.Snack, price = 10),
    Food(17, "Snack 5", R.drawable.snacks_5, FoodType.Snack, price = 10),
    Food(18, "Snack 6", R.drawable.snacks_6, FoodType.Snack, price = 10),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel,cartViewModel: CartViewModel) {
    val uiController = rememberSystemUiController()
    uiController.isStatusBarVisible = false
    val foodsState by homeViewModel.foodList.collectAsState()
    val cartItems by cartViewModel.cartItems.collectAsState()
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text(text = "Our Menu") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = "Back")
                }
            },
            actions = {
                Box(modifier = Modifier.padding(end = 16.dp)) {
                    IconButton(onClick = { navController.navigate("cart") }) {
                        Icon(
                            imageVector = Icons.Rounded.ShoppingCart,
                            contentDescription = "Cart",
                            tint = Color.Black
                        )
                    }
                    // แสดงจุดแดงเตือนถ้ามีของในตะกร้า
                    if (cartItems.isNotEmpty()) {
                        Badge(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .offset(x = (-4).dp, y = 4.dp),
                            containerColor = Color.Red
                        ) {
                            Text(text = cartItems.size.toString(), color = Color.White)
                        }
                    }
                }
            }
        )
    }) { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {
            val selectedFoodType = remember { mutableIntStateOf(0) }
            Spacer(modifier = Modifier.height(16.dp))
            TabLayout(
                items = listOf(
                    "Meals" to {
                        Foods(
                            items = foodsState.filter { it.type == FoodType.Meal },
                            onLikeChange = { food ->
                                homeViewModel.toggleLike(food.id) // เรียก ViewModel
                            },
                            onTap = { food ->
                                navController.navigate("food/${food.id}")
                            }
                        )
                    },
                    "Sides" to {
                        Foods(
                            items = foodsState.filter { it.type == FoodType.Side },
                            onLikeChange = { food ->
                                homeViewModel.toggleLike(food.id)
                            },
                            onTap = { food ->
                                navController.navigate("food/${food.id}")
                            }
                        )
                    },
                    "Snacks" to {
                        Foods(
                            items = foodsState.filter { it.type == FoodType.Snack },
                            onLikeChange = { food ->
                                homeViewModel.toggleLike(food.id)
                            },
                            onTap = { food ->
                                navController.navigate("food/${food.id}")
                            }
                        )
                    },
                ),
                selectedIndex = selectedFoodType.intValue,
                onTabClick = { selectedFoodType.intValue = it },
                textHeight = 30.dp,
                indicatorPadding = PaddingValues(horizontal = 10.dp)
            )
        }
    }
}

@Composable
fun Foods(items: List<Food>, onLikeChange: (Food) -> Unit, onTap: (Food) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xfff1f1f1))
            .padding(horizontal = 8.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(items) { index, food ->
                Card(
                    onClick = { onTap(food) },
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Image(
                            modifier = Modifier
                                .size(25.dp)
                                .clickable { onLikeChange(food) },
                            painter = painterResource(
                                id = if (food.liked) R.drawable.ic_like else R.drawable.ic_unlike
                            ),
                            contentDescription = null
                        )
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Image(
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape),
                            painter = painterResource(id = food.image),
                            contentDescription = food.name,
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = food.name, fontSize = 15.sp, color = Color(0xff383838))
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(text = "${food.price}$")
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}