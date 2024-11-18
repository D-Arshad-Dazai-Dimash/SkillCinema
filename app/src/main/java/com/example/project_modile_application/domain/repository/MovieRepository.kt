package com.example.project_modile_application.domain.repository

import com.example.project_modile_application.domain.dataclasses.MoviesData

interface MovieRepository {
    suspend fun getMovieById(id: Int): MoviesData


}