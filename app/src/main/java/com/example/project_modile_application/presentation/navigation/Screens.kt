package com.example.project_modile_application.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.project_modile_application.data.internet.KinoPoiskApi
import com.example.project_modile_application.data.internet.apiService
import com.example.project_modile_application.data.local.entities.CollectionEntity
import com.example.project_modile_application.domain.dataclasses.Movie
import com.example.project_modile_application.domain.viewModels.HomeViewModel
import com.example.project_modile_application.domain.viewModels.RoomViewModel
import com.example.project_modile_application.domain.viewModels.SharedViewModel
import com.example.project_modile_application.presentation.ui.screen.filmography.FilmographyScreen
import com.example.project_modile_application.presentation.ui.screen.filmpage.FilmPageScreen
import com.example.project_modile_application.presentation.ui.screen.filtrsetting.filtergenre.FilterGenreScreen
import com.example.project_modile_application.presentation.ui.screen.filtrsetting.filtrcountry.FilterCountryScreen
import com.example.project_modile_application.presentation.ui.screen.filtrsetting.filtrperiod.FilterPeriodScreen
import com.example.project_modile_application.presentation.ui.screen.listingPage.IntoCategory_Screen
import com.example.project_modile_application.presentation.ui.screen.profilePage.components.Listing
import com.example.project_modile_application.presentation.ui.screen.searchpage.SearchPage


@Composable
fun Home(navController: NavController, sharedViewModel: SharedViewModel) {
    val homeViewModel = HomeViewModel(apiService)
    com.example.project_modile_application.presentation.ui.screen.home.Home(
        navController,
        homeViewModel,
        sharedViewModel
    )
}


@Composable
fun Search(navController: NavController) {
    SearchPage(navController)
}

@Composable
fun Profile(navController: NavController) {
    com.example.project_modile_application.presentation.ui.screen.profilePage.ProfileScreen(
        navController
    )
}

@Composable
fun OnBoarding(navController: NavController) {
    com.example.project_modile_application.presentation.ui.screen.onboarding.OnBoardingScreen(
        navController
    )
}

@Composable
fun ListingScreen(
    navController: NavController
) {
    Listing(navController)
}

@Composable
fun ListingPage(
    navController: NavController,
    apiService: KinoPoiskApi,
    category: String,
    sharedViewModel: SharedViewModel
) {
    IntoCategory_Screen(
        navController = navController,
        apiService = apiService,
        category,
        sharedViewModel = sharedViewModel
    )
}

@Composable
fun FilmPage(navController: NavController, movie: Movie, roomViewModel: RoomViewModel) {
    FilmPageScreen(navController, movie, roomViewModel)
}

@Composable
fun GalleryPage(navController: NavController, movieId: Int) {
    com.example.project_modile_application.presentation.ui.screen.galleryPage.GalleryPage(
        navController,
        movieId
    )
}

@Composable
fun FilmographyPage(navController: NavController, sharedViewModel: SharedViewModel) {
    FilmographyScreen(navController, sharedViewModel)
}

@Composable
fun SearchPageParameters(navController: NavController) {
    com.example.project_modile_application.presentation.ui.screen.searchPageParameters.SearchPageParameters(
        navController
    )
}

@Composable
fun FilterPeriodPage(navController: NavController) {
    FilterPeriodScreen(navController)
}

@Composable
fun FilterGenrePage(navController: NavController) {
    FilterGenreScreen(navController)
}

@Composable
fun FilterCountryPage(navController: NavController) {
    FilterCountryScreen(navController)
}
