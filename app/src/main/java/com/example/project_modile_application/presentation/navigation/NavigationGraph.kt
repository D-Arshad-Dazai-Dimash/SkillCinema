package com.example.project_modile_application.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.project_modile_application.data.internet.apiService
import com.example.project_modile_application.domain.viewModels.SharedViewModel


@Composable
fun NavigationGraph(navController: NavHostController, sharedViewModel: SharedViewModel) {
    NavHost(navController = navController, startDestination = Screen.Onboarding.route) {
        composable(Screen.Home.route) { Home(navController, sharedViewModel) }
        composable(Screen.Search.route) { Search() }
        composable(Screen.Profile.route) { Profile() }
        composable(Screen.Onboarding.route) { OnBoarding(navController) }
        composable(Screen.ListingPage.route) {
            ListingPage(
                navController = navController,
                apiService,
                "Категория",
                sharedViewModel
            )
        }
        composable("movieData/{id}") {
            sharedViewModel.selectedMovie.value?.let { id ->
                FilmPage(navController, id)
            }
        }

    }
}