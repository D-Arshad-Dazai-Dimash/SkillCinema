package com.example.project_modile_application.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.project_modile_application.data.internet.KinoPoiskApi
import com.example.project_modile_application.data.internet.apiService
import com.example.project_modile_application.domain.dataclasses.Film
import com.example.project_modile_application.domain.viewModels.HomeViewModel
import com.example.project_modile_application.domain.viewModels.MovieDetailViewModel
import com.example.project_modile_application.domain.viewModels.SharedViewModel
import com.example.project_modile_application.presentation.ui.screen.filmography.FilmographyScreen
import com.example.project_modile_application.presentation.ui.screen.filmpage.FilmPageScreen
import com.example.project_modile_application.presentation.ui.screen.listingPage.IntoCategory_Screen


@Composable
fun Home(navController: NavController , sharedViewModel: SharedViewModel) {
    val homeViewModel = HomeViewModel(apiService)
    com.example.project_modile_application.presentation.ui.screen.home.Home(
        navController,
        homeViewModel,
        sharedViewModel
    )
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
    com.example.project_modile_application.presentation.ui.screen.onboarding.OnBoardingScreen(
        navController
    )
}

@Composable
fun ListingPage(navController: NavController, apiService: KinoPoiskApi, category: String , sharedViewModel: SharedViewModel) {
    IntoCategory_Screen(
        navController = navController,
        apiService = apiService,
        category,
        sharedViewModel = sharedViewModel
    )
}

@Composable
fun FilmPage(navController: NavController) {
    FilmPageScreen(navController)
}
@Composable
fun FilmographyPage(navController: NavController, sharedViewModel: SharedViewModel) {
    FilmographyScreen(navController,sharedViewModel)
}

