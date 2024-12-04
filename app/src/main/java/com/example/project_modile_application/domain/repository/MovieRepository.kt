package com.example.project_modile_application.domain.repository

import com.example.project_modile_application.domain.dataclasses.FilmsByKeyWord
import com.example.project_modile_application.domain.dataclasses.Images
import com.example.project_modile_application.domain.dataclasses.Movie
import com.example.project_modile_application.domain.dataclasses.MoviesData
import com.example.project_modile_application.domain.dataclasses.SimilarMovies
import com.example.project_modile_application.domain.dataclasses.StaffData

interface MovieRepository {
    suspend fun getMovieById(id: Int): MoviesData


    suspend fun getActorsById(id: Int): List<StaffData>

    suspend fun getImagesById(id: Int): Images

    suspend fun getSimilarsById(id: Int): SimilarMovies

    suspend fun getFilmsByKeyWord(keyWord: String): FilmsByKeyWord
}