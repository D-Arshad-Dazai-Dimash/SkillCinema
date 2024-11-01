package com.example.project_modile_application.data.model

import kotlinx.serialization.SerialName

data class Movie(
    @SerialName(value = "countries")
    val countries: List<Country>,
    @SerialName(value = "genres")
    val genres: List<Genre>,
    @SerialName(value = "kinopoiskId")
    val kinopoiskId: Int,
    @SerialName(value = "nameEn")
    val nameEn: String,
    @SerialName(value = "nameOriginal")
    val nameOriginal: String,
    @SerialName(value = "nameRu")
    val nameRu: String,
    @SerialName(value = "posterUrl")
    val posterUrl: String,
    @SerialName(value = "ratingImbd")
    val ratingImbd: Double,
    @SerialName(value = "ratingKinopoisk")
    val ratingKinopoisk: Double,
    @SerialName(value = "year")
    val year: String
)
