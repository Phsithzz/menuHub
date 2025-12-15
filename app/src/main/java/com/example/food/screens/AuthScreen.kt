package com.example.food.screens

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.food.components.TabLayout

@Composable
fun AuthScreen(navController: NavController){
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("main", Context.MODE_PRIVATE)

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val selectedTab = remember {
            mutableIntStateOf(0)
        }
        TabLayout(
            selectedTab.intValue,
            items = listOf(
                "Sign In" to {},
                "Sign Up" to {},
            ),
            onTabClick = {
                selectedTab.intValue = it
            }
        )
    }
}

@Composable
fun SignIn(
    navController: NavController,
    sharedPreferences: SharedPreferences
){
    val rememberMeChecked = remember { mutableStateOf(false) }
    val email = remember{
        mutableStateOf("")
    }
    val password = remember{mutableStateOf("")}
    val showPassword = remember{
        mutableStateOf(false)
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Spacer(modifier = Modifier.height(22.dp))
    }
}
