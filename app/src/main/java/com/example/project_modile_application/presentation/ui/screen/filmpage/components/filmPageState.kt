package com.example.project_modile_application.presentation.ui.screen.filmpage.components

import com.example.project_modile_application.domain.dataclasses.MoviesData


data class FilmPageState (
    var isLoading: Boolean = false,
    var movie: MoviesData? = null,
    var error: String = "",
    var filmId: Int? = 0
)