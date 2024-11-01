package com.example.project_modile_application.data.model

import kotlinx.serialization.SerialName

data class Genre(
    @SerialName(value = "id")
    val id: Int = 0,
    @SerialName(value = "genre")
    val name: String
)