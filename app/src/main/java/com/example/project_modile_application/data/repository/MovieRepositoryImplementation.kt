package com.example.project_modile_application.data.repository

import com.example.project_modile_application.data.internet.apiService
import com.example.project_modile_application.domain.dataclasses.FilmsByKeyWord
import com.example.project_modile_application.domain.dataclasses.Images
import com.example.project_modile_application.domain.dataclasses.Movie
import com.example.project_modile_application.domain.dataclasses.MoviesData
import com.example.project_modile_application.domain.dataclasses.SimilarMovies
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

    override suspend fun getImagesById(
        id: Int
    ): Images {
        return apiService.getImagesById(id)
    }

    override suspend fun getSimilarsById(
        id: Int
    ): SimilarMovies {
        return apiService.getSimilarById(id)
    }

    override suspend fun getFilmsByKeyWord(
        keyWord: String
    ): FilmsByKeyWord {
        return apiService.getFilmsByKeyWord(keyWord)
    }
}
