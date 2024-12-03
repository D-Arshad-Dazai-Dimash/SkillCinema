package com.example.project_modile_application.domain.dataclasses.searchPage

sealed class Type(override val uiValue: String, override val apiValue: String) : ApiMappedSearchPageParameter{
    data object All: Type("Все","ALL")
    data object Film: Type("Фильмы","FILM")
    data object Series: Type("Сериалы","TV_SERIES, MINI_SERIES")
}
