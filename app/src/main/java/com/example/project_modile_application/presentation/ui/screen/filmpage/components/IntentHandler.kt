package com.example.project_modile_application.presentation.ui.screen.filmpage.components

import androidx.compose.runtime.Composable
import com.example.project_modile_application.presentation.ui.screen.filmpage.components.state.FilmPageState

fun IntentHandler(
    currentState: FilmPageState,
    result: FilmPageResult
): FilmPageState {
    return when (result) {
        is FilmPageResult.MovieLoaded -> currentState.copy(
            isLoading = false,
            movie = result.movies,
            error = null
        )
        is FilmPageResult.ActorsLoaded -> currentState.copy(
            isLoading = false,
            actors = result.actors,
            error = null
        )
        is FilmPageResult.GaleryLoaded -> currentState.copy(
            isLoading = false,
            galery = result.galery,
            error = null
        )
        is FilmPageResult.SimilarMoviesLoaded -> currentState.copy(
            isLoading = false,
            similarMovies = result.similarMovies,
            error = null
        )
        is FilmPageResult.Error -> currentState.copy(
            isLoading = false,
            error = result.message
        )
    }
}
