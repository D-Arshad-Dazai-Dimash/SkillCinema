package com.example.project_modile_application.data

sealed interface UiState {
    object Initial : UiState
    object Loading : UiState
    data class Success(val movies: List<PosterData>) : UiState
    data class Error(val message: String) : UiState
}
