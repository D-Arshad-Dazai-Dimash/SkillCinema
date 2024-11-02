package com.example.project_modile_application.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.project_modile_application.data.internet.KinoPoiskApi
import com.example.project_modile_application.data.internet.apiService
import com.example.project_modile_application.ui.SharedViewModel
import com.example.project_modile_application.ui.screen.home.HomeViewModel
import com.example.project_modile_application.ui.screen.listingPage.IntoCategory_Screen


val sharedViewModel = SharedViewModel()

@Composable
fun Home(navController: NavController) {
    val homeViewModel = HomeViewModel(apiService)
    com.example.project_modile_application.ui.screen.home.Home(navController, homeViewModel, sharedViewModel)
}


@Composable
fun Search() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Search Screen")
    }
}

@Composable
fun Profile() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Profile Screen")
    }
}

@Composable
fun OnBoarding(navController: NavController) {
    com.example.project_modile_application.ui.screen.onboarding.OnBoardingScreen(navController)
}

@Composable
fun ListingPage(apiService: KinoPoiskApi, category:String) {
    IntoCategory_Screen(apiService = apiService, category, sharedViewModel = sharedViewModel)
}