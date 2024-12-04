package com.example.project_modile_application.domain.dataclasses.searchPage

data class Genre(
    override val id: Int,
    val genre: String
) : IdSearchPageParameter {
    override val value: String
        get() = genre
}