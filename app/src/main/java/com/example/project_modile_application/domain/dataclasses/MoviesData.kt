package com.example.project_modile_application.domain.dataclasses
data class MoviesData(
    val title: String = "",
    val image: String = "",
    val genres: List<Genre> = emptyList(),
    val countries: List<Country> = emptyList(),
    val coverUrl: String = "",
    val description: String = "",
    val filmLength: Int = 0,
    val kinopoiskId: Int = 0,
    val logoUrl: String = "",
    val nameEn: String = "",
    val nameOriginal: String = "",
    val nameRu: String = "",
    val posterUrl: String = "",
    val posterUrlPreview: String = "",
    val ratingKinopoisk: Double = 0.0,
    val shortDescription: String = "",
    val slogan: String = "",
    val type: String = "",
    val webUrl: String = "",
    val year: Int = 0,
    val ratingAgeLimits: String = ""
)
