package com.example.project_modile_application.presentation.ui.screen.searchPageParameters.components.state

import com.example.project_modile_application.domain.dataclasses.searchPage.Country
import com.example.project_modile_application.domain.dataclasses.searchPage.Genre
import java.util.Calendar

data class SearchPageParametersState(
    var countries: Country? = null,
    var genres: Genre? = null,
    var order: String = "YEAR",
    var type: String = "ALL",
    var ratingFrom: Double = 0.0,
    var ratingTo: Double = 10.0,
    var yearFrom: Int = 1900,
    var yearTo: Int = Calendar.getInstance().get(Calendar.YEAR),
    var keyWord: String = "",
    var watched: Boolean = false
)