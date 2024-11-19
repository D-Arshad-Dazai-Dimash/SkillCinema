package com.example.project_modile_application.presentation.ui.screen.filmpage.components.state

import com.example.project_modile_application.domain.dataclasses.SimilarMovies

data class SimilarState(
    var isLoading: Boolean = false,
    var movies: SimilarMovies? = null,
    var error: String = "",
    var id: Int? = 0
)