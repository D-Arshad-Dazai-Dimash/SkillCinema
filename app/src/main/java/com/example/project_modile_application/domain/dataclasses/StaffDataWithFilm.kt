package com.example.project_modile_application.domain.dataclasses

data class StaffDataWithFilm(
    val age: Int,
    val birthday: String,
    val films: List<Film>,
    val nameEn: String,
    val nameRu: String,
    val personId: Int,
    val posterUrl: String,
    val profession: String
)
