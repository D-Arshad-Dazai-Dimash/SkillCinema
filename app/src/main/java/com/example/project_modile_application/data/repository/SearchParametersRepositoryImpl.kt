package com.example.project_modile_application.data.repository

import com.example.project_modile_application.data.internet.apiService
import com.example.project_modile_application.domain.dataclasses.searchPage.GenresAndCountriesResponse
import com.example.project_modile_application.domain.repository.SearchParametersRepository

class SearchParametersRepositoryImpl : SearchParametersRepository {
    override suspend fun getGenresAndCountries(): GenresAndCountriesResponse {
        return apiService.getGenresAndCountries()
    }
}