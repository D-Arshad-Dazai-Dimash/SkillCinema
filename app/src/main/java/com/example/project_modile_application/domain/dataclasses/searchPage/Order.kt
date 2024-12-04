package com.example.project_modile_application.domain.dataclasses.searchPage

sealed class Order(override val uiValue: String, override val apiValue: String) : ApiMappedSearchPageParameter{
    data object Rating : Order("Рейтинг", "RATING")
    data object Popularity: Order("Популярность", "NUM_VOTE")
    data object Date: Order("Дата","YEAR")
}