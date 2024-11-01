package com.example.project_modile_application.data.model

import kotlinx.serialization.SerialName

data class MoviesList(
    @SerialName(value = "total")
    val total: Int = 0,
    @SerialName(value = "items")
    val movies: List<Movie>,
    @SerialName(value = "totalPages")
    val totalPages: Int = 0
)