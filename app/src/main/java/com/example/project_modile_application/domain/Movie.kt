package com.example.project_modile_application.domain

data class Movie(
    val kinopoiskId: Int,
    val title: String,
    val image: String,
    val nameRu: String?,
    val nameEn: String?,
    val posterUrl: String?,
    val posterUrlPreview: String?,
    val genres: List<Genre>,
    val countries: List<Country>,
    val year: Int
)