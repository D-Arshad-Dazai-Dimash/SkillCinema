package com.example.project_modile_application.data.model

import kotlinx.serialization.SerialName

data class Country(
    @SerialName(value = "country")
    val country: String
)