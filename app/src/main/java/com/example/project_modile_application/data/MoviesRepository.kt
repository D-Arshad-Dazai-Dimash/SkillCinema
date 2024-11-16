package com.example.project_modile_application.data

import com.example.project_modile_application.data.internet.KinoPoiskApi

class MoviesRepository(private val api: KinoPoiskApi){
    suspend fun getFilmById(filmId: Int) : MoviesData {
        return api.getFilmById(filmId)
    }
}