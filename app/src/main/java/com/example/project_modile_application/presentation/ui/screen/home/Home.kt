package com.example.project_modile_application.presentation.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.project_modile_application.R
import com.example.project_modile_application.data.Categories
import com.example.project_modile_application.domain.viewModels.HomeViewModel
import com.example.project_modile_application.domain.viewModels.SharedViewModel
import com.example.project_modile_application.presentation.navigation.Screen
import com.example.project_modile_application.presentation.ui.screen.home.components.Category


@Composable
fun Home(
    navController: NavController,
    homeViewModel: HomeViewModel,
    sharedViewModel: SharedViewModel
) {
    val premiers = homeViewModel.premiers.value
    val popular = homeViewModel.popular.value
    val top250 = homeViewModel.top250.value
    val onClick = fun(category: Categories) {
        navController.navigate(Screen.ListingPage.route)
        sharedViewModel.updateCategory(category)
    }
    Column(Modifier.padding(start = 26.dp, end = 26.dp, top = 55.dp)) {
        Image(
            painter = painterResource(R.drawable.skillcinema),
            contentDescription = "",
            modifier = Modifier
                .padding(bottom = 10.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState(0)),
        ) {
            Category(premiers, onClick, Categories.Premieres, navController , sharedViewModel)
            Category(popular, onClick, Categories.Popular, navController , sharedViewModel)
            Category(top250, onClick, Categories.Top250, navController , sharedViewModel)
        }
    }
}