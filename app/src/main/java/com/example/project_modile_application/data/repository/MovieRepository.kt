package com.example.project_modile_application.data.repository

import com.example.project_modile_application.domain.dataclasses.MoviesData
import com.example.project_modile_application.data.internet.KinoPoiskApi
import com.example.project_modile_application.data.internet.apiService
import com.example.project_modile_application.data.internet.retrofit
import com.example.project_modile_application.domain.dataclasses.StaffData
import com.example.project_modile_application.domain.repository.MovieRepository

class MovieRepositoryImplementation : MovieRepository {
    override suspend fun getMovieById(id: Int): MoviesData {
        return apiService.getFilmById(id)
    }

    override suspend fun getActorsById(
        id: Int,
    ): List<StaffData> {
        return apiService.getActors(id)
    }
}