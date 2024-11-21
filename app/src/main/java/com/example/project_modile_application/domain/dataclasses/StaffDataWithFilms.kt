package com.example.project_modile_application.domain.dataclasses

data class StaffDataWithFilms(
    val personId: Int,
    val nameRu: String,
    val nameEn: String?,
    val posterUrl: String?,
    val profession: String,
    val films: List<Film>
)
