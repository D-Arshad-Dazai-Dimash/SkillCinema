package com.example.project_modile_application.presentation.ui.screen.filmpage.components.state

import com.example.project_modile_application.domain.dataclasses.Images
import com.example.project_modile_application.domain.dataclasses.MoviesData
import com.example.project_modile_application.domain.dataclasses.SimilarMovies
import com.example.project_modile_application.domain.dataclasses.StaffData


data class FilmPageState(
    var isLoading: Boolean = false,
    var movie: MoviesData? = null,
    var error: String? = "",
    var filmId: Int? = 0,
    var actors: List<StaffData>? = null,
    var similarMovies: SimilarMovies? = null,
    var galery: Images? = null,
    var id: Int? = 0


)

