package com.example.project_modile_application.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.project_modile_application.ui.screen.GenresUiState
import com.example.project_modile_application.ui.screen.HomeViewModel
import com.example.project_modile_application.ui.screen.MoviesUiState
import com.example.project_modile_application.ui.screen.MoviesViewModel

@Composable
fun Home(homeViewModel: HomeViewModel = viewModel(),
         moviesViewModel: MoviesViewModel = viewModel()
) {
    val genresUiState by moviesViewModel.genresUiState.collectAsState(GenresUiState.Loading)
    val moviesUiState by homeViewModel.moviesUiState.collectAsState(MoviesUiState.Loading)

    com.example.project_modile_application.ui.screen.home.Home(
        genresUiState,
        moviesUiState)
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