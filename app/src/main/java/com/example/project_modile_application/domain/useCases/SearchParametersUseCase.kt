package com.example.project_modile_application.domain.useCases

import com.example.project_modile_application.data.repository.SearchParametersRepositoryImpl
import com.example.project_modile_application.domain.dataclasses.searchPage.Country
import com.example.project_modile_application.domain.dataclasses.searchPage.Genre

class SearchParametersUseCase {
    private val searchParametersRepository = SearchParametersRepositoryImpl()
    suspend fun getCountries(): List<Country> {
        return searchParametersRepository.getGenresAndCountries().countries
    }
    suspend fun getGenres(): List<Genre> {
        return searchParametersRepository.getGenresAndCountries().genres
    }
}