package com.example.project_modile_application.domain

data class Movie(
    val kinopoiskId: Int,
    val nameRu: String? = null,
    val posterUrl: String? = null,
    val genres: List<Genre>?  = null,
    val countries: List<Country>? = null
)
