package com.example.project_modile_application.domain.dataclasses

data class FilmsByKeyWord(
    val films: List<FilmX>,
    val keyword: String,
    val pagesCount: Int,
    val searchFilmsCountResult: Int
)