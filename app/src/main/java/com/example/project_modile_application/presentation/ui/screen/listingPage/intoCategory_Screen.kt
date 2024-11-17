package com.example.project_modile_application.presentation.ui.screen.listingPage

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.project_modile_application.data.Categories
import com.example.project_modile_application.data.UiState
import com.example.project_modile_application.data.internet.KinoPoiskApi
import com.example.project_modile_application.domain.Movie
import com.example.project_modile_application.domain.SharedViewModel
import com.example.project_modile_application.presentation.ui.screen.UIStateScreens.ErrorUIState
import com.example.project_modile_application.presentation.ui.screen.UIStateScreens.LoadingUIState

@Composable
fun IntoCategory_Screen(
    navController: NavController,
    apiService: KinoPoiskApi,
    category: String,
    sharedViewModel: SharedViewModel
) {
    var screenState by remember { mutableStateOf<UiState>(UiState.Initial) }
    val movies = remember { mutableStateOf<List<Movie>>(emptyList()) }

    LaunchedEffect(category) {
        screenState = UiState.Loading
        try {
            val response = when (sharedViewModel.category.value) {
                Categories.Premieres -> apiService.getMovies(yearFrom = 2024)
                Categories.Popular -> apiService.getMovies(order = "NUM_VOTE")
                Categories.Top250 -> apiService.getMovies(order = "RATING", ratingFrom = 8)
            }

            if (response.isSuccessful) {
                val movieList = response.body()?.items?.map { it ->
                    Movie(
                        kinopoiskId = it.kinopoiskId ?: -1,
                        title = it.nameRu ?: "Unknown Title",
                        image = it.posterUrl ?: "",
                        genres = it.genres ?: emptyList(),
                        countries = it.countries ?: emptyList(),
                        nameEn = it.nameEn ?: "",
                        nameRu = it.nameRu ?: "",
                        posterUrlPreview = it.posterUrlPreview ?: "",
                        year = it.year ?: 0,
                        posterUrl = it.posterUrl ?: ""
                    )
                } ?: emptyList()
                Log.d("APIResponse", response.body().toString())
                movies.value = movieList
                screenState = UiState.Success(movies = movieList)
            } else {
                screenState = UiState.Error("Error loading movies: ${response.code()}")
            }
        } catch (a: Exception) {
            screenState = UiState.Error("Network error: ${a.message}")
        }
    }

    when (screenState) {
        is UiState.Initial -> {
            Text("Welcome! Tap to load movies.", modifier = Modifier.padding(16.dp))
        }

        is UiState.Loading -> {
            LoadingUIState()
        }

        is UiState.Success -> {
            IntoCategory_Grid(navController = navController, movies = movies.value, sharedViewModel)
        }

        is UiState.Error -> {
            ErrorUIState(
                navController = navController,
                message = (screenState as UiState.Error).message
            )
        }
    }
}
