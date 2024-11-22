package com.example.project_modile_application.domain.dataclasses

data class StaffDataWithFilms(
    val age: Int,
    var films: List<Film>,
    val growth: String,
    val hasAwards: Int,
    val nameEn: String,
    val nameRu: String,
    val personId: Int,
    val posterUrl: String,
    val profession: String
)