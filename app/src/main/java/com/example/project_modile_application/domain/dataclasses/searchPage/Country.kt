package com.example.project_modile_application.domain.dataclasses.searchPage

data class Country(
    override val id: Int,
    val country: String
) : IdSearchPageParameter {
    override val value: String
        get() = country
}