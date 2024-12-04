package com.example.project_modile_application.domain.repository

import com.example.project_modile_application.domain.dataclasses.searchPage.GenresAndCountriesResponse

interface SearchParametersRepository {
    suspend fun getGenresAndCountries(): GenresAndCountriesResponse
}