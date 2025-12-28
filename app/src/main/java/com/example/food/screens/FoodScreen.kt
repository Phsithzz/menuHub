package com.example.food.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.shadow(8.dp),
                navigationIcon = {

                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                            .clickable {
                                navController.popBackStack()
                            }
                    )

                },
                title = {
                    Text(text = "Food")
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            val food = remember {
                foods.random()
            }

            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                painter = painterResource(id = food.image),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text(text = food.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "${food.price}$", fontSize = 18.sp)

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Description", fontSize = 14.sp, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(2.dp))

                Text(

                    text = "Our menu is carefully designed to offer a wide variety of delicious dishes made from high-quality, fresh ingredients. Each item is thoughtfully prepared to deliver great taste, balanced nutrition, and an enjoyable dining experience. Whether you are looking for a satisfying main meal, a tasty side dish, or a light snack, our menu provides options for every appetite and lifestyle.",
                    fontSize = 14.sp,
                    textAlign = TextAlign.Justify,
                    color = Color(0xff313131)

                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Divider(thickness = 2.dp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Recommended Foods:", modifier = Modifier.padding(horizontal = 8.dp),
                fontWeight = FontWeight.Bold, fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(16.dp))

            LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                items(foods){food->
                    RecommendedFood(food = food, onTap = {food->
                    })
                }
            }
            Spacer(modifier =  Modifier.height(16.dp))
            Divider(thickness = 2.dp)
            Spacer(modifier =  Modifier.height(16.dp))
            Column(
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Text(text = "Rating & Reviews", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier =  Modifier.height(8.dp))
                Text(
                    text = "The food was absolutely delicious! The flavors were well balanced, the ingredients tasted fresh, and the portion size was perfect. I would definitely order this again.",
                    fontSize = 14.sp,
                    textAlign = TextAlign.Justify,
                    color = Color(0xff313131)
                )

            }
            Spacer(modifier =  Modifier.height(8.dp))


        }
    }
}


@Composable
fun RecommendedFood(food: Food,onTap:(Food)->Unit){
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xfff1f1f1)
        ),
        onClick = {
            onTap(food)
        }
    ) {
        Column{
            Image(
                modifier = Modifier.sizeIn(maxWidth = 120.dp, maxHeight = 70.dp),
                painter = painterResource(id = food.image),
                contentDescription = food.name,
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = food.name, color = Color(0xff313131),fontSize = 14.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "${food.price}$")
                Spacer(modifier = Modifier.height(4.dp))

            }
        }
    }
}