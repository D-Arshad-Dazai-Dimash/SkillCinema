package com.example.project_modile_application.ui.screen.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project_modile_application.data.model.Movie
import com.example.project_modile_application.ui.font.GraphicFontFamily
import com.example.project_modile_application.ui.screen.HomeViewModel
import com.example.project_modile_application.ui.screen.MoviesUiState

@Composable
fun Category(categoryName: String, genreId: Int,
             viewModel: HomeViewModel = HomeViewModel(),) {
    val moviesUiState by viewModel.moviesUiState.collectAsState(initial = MoviesUiState.Loading)

    LaunchedEffect(genreId) {
        viewModel.loadMovies(genreId) // Load movies when the screen is displayed
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 36.dp,
                bottom = 24.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = categoryName,
            fontFamily = GraphicFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            color = Color(0xff272727)
        )
        Text(
            text = "Все",
            modifier = Modifier.clickable(onClick = {}),
            fontFamily = GraphicFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            color = Color(0xFF3D3BFF)
        )
    }
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        when (moviesUiState) {
            is MoviesUiState.Loading -> {
                // Display a loading indicator
                item {
                    Box(
                        modifier = Modifier.size(100.dp) // Set size for Box
                    ) {
                        CircularProgressIndicator() // Or any loading UI
                    }
                }
            }
            is MoviesUiState.Success -> {
                val movies = (moviesUiState as MoviesUiState.Success).moviesList.movies
                items(movies) {
                    MovieTab(it)
                }
            }
            is MoviesUiState.Error -> {
                item {
                    Box(
                        modifier = Modifier.fillMaxSize() // or set a specific size
                    ) {
                        Text("Error loading movies", color = Color.Red) // Or any error UI
                    }
                }
            }
        }
        item {
            ShowAll()
        }

    }
}