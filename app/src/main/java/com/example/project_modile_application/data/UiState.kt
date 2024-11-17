package com.example.project_modile_application.data

import com.example.project_modile_application.domain.Movie

sealed interface UiState {
    object Initial : UiState
    object Loading : UiState
    data class Success(val movies: List<Movie>) : UiState
    data class Error(val message: String) : UiState
}
