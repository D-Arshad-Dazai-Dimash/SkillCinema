package com.example.project_modile_application.data.model

data class Item(
    val countries: List<Country>,
    val genres: List<Genre>,
    val kinopoiskId: Int,
    val nameEn: String,
    val nameOriginal: String,
    val nameRu: String,
    val posterUrl: String,
    val ratingImbd: Double,
    val ratingKinopoisk: Double,
    val year: String
)