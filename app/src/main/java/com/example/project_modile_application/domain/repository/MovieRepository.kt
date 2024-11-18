package com.example.project_modile_application.domain.repository

import com.example.project_modile_application.domain.dataclasses.MoviesData
import com.example.project_modile_application.domain.dataclasses.StaffData

interface MovieRepository {
    suspend fun getMovieById(id: Int): MoviesData

    suspend fun getActorsById(id : Int): List<StaffData>
}