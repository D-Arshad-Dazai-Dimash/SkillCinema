package com.example.project_modile_application.data

import com.example.project_modile_application.domain.dataclasses.Movie

data class MovieResponse(
    val total: Int,
    val totalPages: Int,
    val items: List<Movie>
)
