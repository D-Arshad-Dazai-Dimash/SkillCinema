package com.example.project_modile_application.data

import com.example.project_modile_application.data.dataclasses.MoviesData

data class MovieResponse(
    val total: Int,
    val totalPages: Int,
    val items: List<MoviesData>
)
