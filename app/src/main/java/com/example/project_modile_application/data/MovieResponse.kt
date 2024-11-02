package com.example.project_modile_application.data

import com.example.project_modile_application.model.Movie

data class MovieResponse(
    val total: Int,
    val totalPages: Int,
    val items: List<Movie>
)
