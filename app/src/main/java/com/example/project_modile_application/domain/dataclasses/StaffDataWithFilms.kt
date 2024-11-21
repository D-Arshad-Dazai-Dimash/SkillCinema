package com.example.project_modile_application.domain.dataclasses

data class StaffDataWithFilms(
    val age: Int,
    val birthday: String,
    val birthplace: String,
    val death: String,
    val deathplace: String,
    val facts: List<String>,
    val films: List<Film>,
    val growth: String,
    val hasAwards: Int,
    val nameEn: String,
    val nameRu: String,
    val personId: Int,
    val posterUrl: String,
    val profession: String,
    val sex: String,
    val spouses: List<SpouseX>,
    val webUrl: String
)