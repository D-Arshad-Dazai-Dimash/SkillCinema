package com.example.project_modile_application.data.model

data class MoviesList(
    val items: List<Item>,
    val total: Int = 0,
    val totalPages: Int = 0
)