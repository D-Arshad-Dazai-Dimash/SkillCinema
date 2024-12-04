package com.example.project_modile_application.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.project_modile_application.data.internet.apiService
import com.example.project_modile_application.domain.viewModels.MovieDetailViewModel
import com.example.project_modile_application.domain.viewModels.SharedViewModel
import com.example.project_modile_application.presentation.ui.screen.actorpage.ActorPageScreen
import com.example.project_modile_application.presentation.ui.screen.filmography.FilmographyScreen


@Composable
fun NavigationGraph(navController: NavHostController, sharedViewModel: SharedViewModel) {
    NavHost(navController = navController, startDestination = Screen.Onboarding.route) {
        composable(Screen.Home.route) { Home(navController, sharedViewModel) }
        composable(Screen.Search.route) { Search(navController) }
        composable(Screen.Profile.route) { Profile(navController , sharedViewModel) }
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
                FilmPage(navController, id , sharedViewModel)
            }
        }
        composable(Screen.GalleryPage.route) {
            sharedViewModel.selectedMovie.value?.kinopoiskId.let { id ->
                if (id != null) {
                    GalleryPage(navController, id)
                }
            }
        }
        composable("staffDetails/{id}") {
            ActorPageScreen(navController,sharedViewModel)
        }
        composable("filmography") {
            FilmographyPage(navController,sharedViewModel)
        }

    }
}