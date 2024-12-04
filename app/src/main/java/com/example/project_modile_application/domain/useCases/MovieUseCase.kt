package com.example.project_modile_application.domain.useCases

import com.example.project_modile_application.data.internet.apiService
import com.example.project_modile_application.data.repository.MovieRepositoryImplementation
import com.example.project_modile_application.domain.dataclasses.FilmsByKeyWord
import com.example.project_modile_application.domain.dataclasses.Images
import com.example.project_modile_application.domain.dataclasses.Movie
import com.example.project_modile_application.domain.dataclasses.MoviesData
import com.example.project_modile_application.domain.dataclasses.SimilarMovies
import com.example.project_modile_application.domain.dataclasses.StaffData

class MovieUseCase {
    private val movieRepository = MovieRepositoryImplementation()

    suspend fun getDetailMovie(id: Int): MoviesData {
        return movieRepository.getMovieById(id)
    }

    suspend fun getActors(
        id: Int,
    ): List<StaffData> {
        return movieRepository.getActorsById(id)
    }

    suspend fun getImages(
        id: Int
    ): Images {
        return movieRepository.getImagesById(id)
    }

    suspend fun getSimilars(
        id: Int
    ): SimilarMovies {
        return movieRepository.getSimilarsById(id)
    }

    suspend fun getFilmsByKeyWord(
        keyWord: String
    ): FilmsByKeyWord {
        return movieRepository.getFilmsByKeyWord(keyWord)
    }
}