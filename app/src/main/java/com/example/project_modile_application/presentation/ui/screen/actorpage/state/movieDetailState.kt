package com.example.project_modile_application.presentation.ui.screen.actorpage.state

import com.example.project_modile_application.domain.dataclasses.MoviesData

data class MovieDetailState(
    var isLoading: Boolean = false,
    var movie: MoviesData?= null,
    var error: String = "",
    var id: Int? = 0
)
