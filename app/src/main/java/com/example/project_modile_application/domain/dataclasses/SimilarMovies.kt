package com.example.project_modile_application.domain.dataclasses

data class SimilarMovies(
    val items: List<SimilarMovie>,
    val total: Int
)

data class SimilarMovie(
    val filmId: Int,
    val nameEn: String,
    val nameOriginal: String,
    val nameRu: String,
    val posterUrl: String,
    val posterUrlPreview: String,
    val relationType: String
)