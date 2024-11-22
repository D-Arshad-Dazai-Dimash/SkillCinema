package com.example.project_modile_application.presentation.ui.screen.filmpage.components

import com.example.project_modile_application.domain.dataclasses.Images
import com.example.project_modile_application.domain.dataclasses.MoviesData
import com.example.project_modile_application.domain.dataclasses.SimilarMovies
import com.example.project_modile_application.domain.dataclasses.StaffData

sealed class FilmPageResult {
    data class MovieLoaded(val movies: MoviesData) : FilmPageResult()
    data class ActorsLoaded(val actors: List<StaffData>) : FilmPageResult()
    data class GaleryLoaded(val galery: Images) : FilmPageResult()
    data class SimilarMoviesLoaded(val similarMovies: SimilarMovies) : FilmPageResult()
    data class Error(val message: String) : FilmPageResult()
}