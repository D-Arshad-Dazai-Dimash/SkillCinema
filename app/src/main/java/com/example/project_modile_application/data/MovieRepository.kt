package com.example.project_modile_application.data

import com.example.project_modile_application.data.model.MoviesList
import com.example.project_modile_application.data.network.MoviesApiService

class MovieRepository(private val apiService: MoviesApiService) {

    suspend fun getMoviesForGenre(genreId: Int): MoviesList {
        return apiService.getMoviesByGenre(genreId)
    }
}