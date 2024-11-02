package com.example.project_modile_application.ui.screen.listingPage


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
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
import com.example.project_modile_application.data.PosterData
import com.example.project_modile_application.data.UiState
import com.example.project_modile_application.data.internet.KinoPoiskApi
import com.example.project_modile_application.ui.SharedViewModel
import com.example.project_modile_application.ui.screen.UIStateScreens.ErrorUIState
import com.example.project_modile_application.ui.screen.UIStateScreens.LoadingUIState

@Composable
fun IntoCategory_Screen(
    navController: NavController,
    apiService: KinoPoiskApi,
    category: String,
    sharedViewModel: SharedViewModel
) {
    var screenState by remember { mutableStateOf<UiState>(UiState.Initial) }
    val movies = remember { mutableStateOf<List<PosterData>>(emptyList()) }


    LaunchedEffect(category) {
        screenState = UiState.Loading
        try {
            val response = when (sharedViewModel.category.value) {
                Categories.Premieres -> apiService.getMovies(yearFrom = 2023)
                Categories.Popular -> apiService.getMovies(order = "NUM_VOTE")
                Categories.Top250 -> apiService.getMovies(order = "RATING", ratingFrom = 8)
            }


            if (response?.isSuccessful == true) {
                val movieList = response.body()?.items?.map {
                    PosterData(
                        title = it.nameRu ?: "Unknown",
                        image = it.posterUrl ?: "",
                        genres = it.genres?.map { genre -> genre.genre } ?: emptyList(),
                        countries = it.countries?.map { country -> country.country } ?: emptyList()
                    )
                } ?: emptyList()

                movies.value = movieList
                screenState = UiState.Success(movies = movieList)
            } else {
                screenState = UiState.Error("Error loading movies: ${response?.code()}")
            }
        } catch (e: Exception) {
            screenState = UiState.Error("Network error: ${e.message}")
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
            IntoCategory_Grid(navController = navController, movies = movies.value)
        }

        is UiState.Error -> {
            ErrorUIState(navController = navController, message = (screenState as UiState.Error).message)

        }
    }
}
