package com.example.project_modile_application.data

data class MovieResponse(
    val total: Int,
    val totalPages: Int,
    val items: List<MoviesData>
)
