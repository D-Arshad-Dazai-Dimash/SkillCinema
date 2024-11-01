package com.example.project_modile_application.data.model

import kotlinx.serialization.SerialName

data class GenresList(
    @SerialName(value = "genres")
    val genres: List<Genre>
)