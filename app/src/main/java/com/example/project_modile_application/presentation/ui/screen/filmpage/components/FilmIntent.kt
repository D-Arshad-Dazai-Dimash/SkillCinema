package com.example.project_modile_application.presentation.ui.screen.filmpage.components

sealed class FilmIntent {
    data class LoadMovies(val movieId: Int): FilmIntent()
    data class LoadMovie(val movieId: Int): FilmIntent()
    data class LoadActors(val movieId: Int): FilmIntent()
    data class LoadGallery(val movieId: Int): FilmIntent()
    data class LoadSimilarMovies(val movieId: Int): FilmIntent()
    //....... Джиги осында қосасыңдар
}