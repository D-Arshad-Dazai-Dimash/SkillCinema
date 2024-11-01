package com.example.project_modile_application.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.project_modile_application.R
import com.example.project_modile_application.ui.screen.GenresUiState
import com.example.project_modile_application.ui.screen.HomeViewModel
import com.example.project_modile_application.ui.screen.MoviesUiState
import com.example.project_modile_application.ui.screen.MoviesViewModel
import com.example.project_modile_application.ui.screen.home.components.Category

@Composable
fun HomePrev(
    genresUiState: GenresUiState,
    moviesUiState: MoviesUiState,
    modifier: Modifier = Modifier
) {
    Home(genresUiState = genresUiState, moviesUiState = moviesUiState)
}

@Composable
fun Home(
    genresUiState: GenresUiState,
    moviesUiState: MoviesUiState,
    modifier: Modifier = Modifier
) {
    when (genresUiState) {
        is GenresUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is GenresUiState.Success ->
            Column(Modifier.padding(start = 26.dp, end = 26.dp, top = 55.dp)) {
                Image(
                    painter = painterResource(R.drawable.skillcinema),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState(0)),
                ) {
                    items( genresUiState.genresList.genres.size ) { index ->
                        when(moviesUiState) {
                            is MoviesUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
                            is MoviesUiState.Success ->
                                Category(genresUiState.genresList.genres[index].name, moviesUiState.moviesList.movies)
                            is MoviesUiState.Error -> ErrorScreen( modifier = modifier.fillMaxSize())
                        }
                    }
//                    Category("Премьеры", moviesApiService.loadPremieres())
//                    Category("Популярное",moviesApiService.loadPopularMovies())
//                    Category("Боевики США", moviesApiService.loadActionMovies())
//                    Category("Драмы Франции", moviesApiService.loadDramaMovies())
//                    Category("Сериалы", moviesApiService.loadSeries())
                }

            }

        is GenresUiState.Error -> ErrorScreen( modifier = modifier.fillMaxSize())
    }

}
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    errorMessage: String = "An error occurred",
    onRetry: () -> Unit = {}
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFE57373))  // Light red background
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = errorMessage,
                color = Color.White,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Button(onClick = onRetry) {
                Text(text = "Retry")
            }
        }
    }
}

