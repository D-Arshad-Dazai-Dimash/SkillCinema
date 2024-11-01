package com.example.project_modile_application.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_modile_application.data.model.MoviesList
import com.example.project_modile_application.data.network.MoviesApi
import kotlinx.coroutines.launch

sealed interface MoviesUiState {
    data class Success(val moviesList: MoviesList) : MoviesUiState
    object Error : MoviesUiState
    object Loading : MoviesUiState
}

class MoviesViewModel : ViewModel() {
    var moviesUiState: MoviesUiState by mutableStateOf(MoviesUiState.Loading)
        private set

    private fun getMoviesList() {
        viewModelScope.launch {
            try {
                val listResult = MoviesApi.retrofitService.getMovieList()
                moviesUiState = MoviesUiState.Success(listResult)
            } catch (e: Exception) {
                moviesUiState = MoviesUiState.Error
            }
        }
    }
}