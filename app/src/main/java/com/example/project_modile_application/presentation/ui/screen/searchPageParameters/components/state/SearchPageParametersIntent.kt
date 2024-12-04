package com.example.project_modile_application.presentation.ui.screen.searchPageParameters.components.state

import com.example.project_modile_application.domain.dataclasses.searchPage.Country
import com.example.project_modile_application.domain.dataclasses.searchPage.Genre
import com.example.project_modile_application.domain.dataclasses.searchPage.Order
import com.example.project_modile_application.domain.dataclasses.searchPage.Type

sealed class SearchPageParametersIntent {
    data class SetCountry(val country: Country) : SearchPageParametersIntent()
    data class SetGenre(val genre: Genre) : SearchPageParametersIntent()
    data class SetYear(val year: IntRange) : SearchPageParametersIntent()
    data class SetRating(val rating: ClosedFloatingPointRange<Float>) : SearchPageParametersIntent()
    data class SetOrder(val order: Order) : SearchPageParametersIntent()
    data class SetType(val type: Type) : SearchPageParametersIntent()
    data object SetWatched : SearchPageParametersIntent()
}