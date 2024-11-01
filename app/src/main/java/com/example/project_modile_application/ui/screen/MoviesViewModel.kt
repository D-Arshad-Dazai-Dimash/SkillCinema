package com.example.project_modile_application.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_modile_application.data.model.GenresList
import com.example.project_modile_application.data.model.MoviesList
import com.example.project_modile_application.data.network.MoviesApi
import kotlinx.coroutines.launch

sealed interface MoviesUiState {
    data class Success(val moviesList: MoviesList) : MoviesUiState
    object Error : MoviesUiState
    object Loading : MoviesUiState
}
sealed interface GenresUiState {
    data class Success(val genresList: GenresList) : GenresUiState
    object Error : GenresUiState
    object Loading : GenresUiState
}

class MoviesViewModel : ViewModel() {
    var moviesUiState: MoviesUiState by mutableStateOf(MoviesUiState.Loading)
        private set

    var genresUiState: GenresUiState by mutableStateOf(GenresUiState.Loading)
        private set

    private fun getMoviesList() {
        viewModelScope.launch {
            try {
                val listResult = MoviesApi.retrofitService.getMoviesList()
                moviesUiState = MoviesUiState.Success(listResult)
            } catch (e: Exception) {
                moviesUiState = MoviesUiState.Error
            }
        }
    }

    private fun getGenreList() {
        viewModelScope.launch {
            try {
                val listResult = MoviesApi.retrofitService.getGenresList()
                genresUiState = GenresUiState.Success(listResult)
            } catch (e: Exception) {
                genresUiState = GenresUiState.Error
            }
        }
    }

    private fun getMoviesByGenre(genreId: Int) {
        viewModelScope.launch {
            try {
                val listResult = MoviesApi.retrofitService.getMoviesByGenre(genreId)
                moviesUiState = MoviesUiState.Success(listResult)
            } catch (e: Exception) {
                moviesUiState = MoviesUiState.Error
            }
        }
    }
}