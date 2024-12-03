package com.example.project_modile_application.presentation.ui.screen.searchpage.state

import com.example.project_modile_application.domain.dataclasses.FilmX

data class SearchDetailState (
    var isLoading: Boolean = false,
    var films: List<FilmX>?= null,
    var error: String = "",
    var id: Int? = 0
)