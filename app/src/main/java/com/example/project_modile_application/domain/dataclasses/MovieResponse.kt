package com.example.project_modile_application.domain.dataclasses

data class MovieResponse(
    val total: Int,
    val totalPages: Int,
    val items: List<Movie>
)


