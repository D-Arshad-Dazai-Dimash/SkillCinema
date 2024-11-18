package com.example.project_modile_application.domain.dataclasses

data class MoviesData(
    val title: String,
    val image: String,
    val genres: List<Genre>,
    val countries: List<Country>,
    val coverUrl: String,
    val description: String,
    val filmLength: Int,
    val kinopoiskId: Int,
    val logoUrl: String,
    val nameEn: String,
    val nameOriginal: String,
    val nameRu: String,
    val posterUrl: String,
    val posterUrlPreview: String,
    val ratingKinopoisk: Double,
    val shortDescription: String,
    val slogan: String,
    val type: String,
    val webUrl: String,
    val year: Int,
    val ratingAgeLimits : String
)


