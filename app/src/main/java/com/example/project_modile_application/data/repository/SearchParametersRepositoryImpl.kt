package com.example.project_modile_application.data.repository

import android.util.Log
import retrofit2.HttpException
import com.example.project_modile_application.data.internet.apiService
import com.example.project_modile_application.domain.dataclasses.searchPage.GenresAndCountriesResponse
import com.example.project_modile_application.domain.repository.SearchParametersRepository

class SearchParametersRepositoryImpl : SearchParametersRepository {
    override suspend fun getGenresAndCountries(): GenresAndCountriesResponse {
        return try {
            apiService.getGenresAndCountries()
        } catch (e: HttpException){
            Log.e("SearchParametersRepo", "Unexpected exception: ${e.message} ${e.javaClass}")
            GenresAndCountriesResponse(listOf(), listOf())
        }
    }
}