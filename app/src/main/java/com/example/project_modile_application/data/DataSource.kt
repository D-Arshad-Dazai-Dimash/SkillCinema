package com.example.project_modile_application.data

import com.example.project_modile_application.R
import com.example.project_modile_application.model.Movie

class DataSource {
    fun loadPremieres() : List<Movie> {
        return listOf<Movie> (
            Movie(R.drawable.movie, R.string.premiere_movie1, R.string.horror),
            Movie(R.drawable.movie, R.string.premiere_movie2, R.string.action),
            Movie(R.drawable.movie, R.string.premiere_movie3, R.string.drama),
            Movie(R.drawable.movie, R.string.premiere_movie4, R.string.horror),
            Movie(R.drawable.movie, R.string.premiere_movie5, R.string.drama),
            Movie(R.drawable.movie, R.string.premiere_movie6, R.string.animation),
            Movie(R.drawable.movie, R.string.premiere_movie7, R.string.drama),
            Movie(R.drawable.movie, R.string.premiere_movie8, R.string.animation)
        )
    }
    fun loadPopularMovies() : List<Movie> {
        return listOf<Movie> (
            Movie(R.drawable.movie, R.string.popular_movie1, R.string.drama),
            Movie(R.drawable.movie, R.string.popular_movie2, R.string.fantasy),
            Movie(R.drawable.movie, R.string.popular_movie3, R.string.action),
            Movie(R.drawable.movie, R.string.popular_movie4, R.string.animation),
            Movie(R.drawable.movie, R.string.popular_movie5, R.string.action),
            Movie(R.drawable.movie, R.string.popular_movie6, R.string.horror),
            Movie(R.drawable.movie, R.string.popular_movie7, R.string.action),
            Movie(R.drawable.movie, R.string.popular_movie8, R.string.fantasy)
        )
    }
    fun loadActionMovies() : List<Movie> {
        return listOf<Movie> (
            Movie(R.drawable.movie, R.string.action_movie1, R.string.action),
            Movie(R.drawable.movie, R.string.action_movie2, R.string.action),
            Movie(R.drawable.movie, R.string.action_movie3, R.string.action),
            Movie(R.drawable.movie, R.string.action_movie4, R.string.action),
            Movie(R.drawable.movie, R.string.action_movie5, R.string.action),
            Movie(R.drawable.movie, R.string.action_movie6, R.string.action)
        )
    }
    fun loadDramaMovies() : List<Movie> {
        return listOf<Movie> (
            Movie(R.drawable.movie, R.string.drama_movie1, R.string.drama),
            Movie(R.drawable.movie, R.string.drama_movie2, R.string.drama),
            Movie(R.drawable.movie, R.string.drama_movie3, R.string.drama),
            Movie(R.drawable.movie, R.string.drama_movie4, R.string.drama),
            Movie(R.drawable.movie, R.string.drama_movie5, R.string.drama),
            Movie(R.drawable.movie, R.string.drama_movie6, R.string.drama)
        )
    }
    fun loadSeries() : List<Movie> {
        return listOf<Movie> (
            Movie(R.drawable.movie, R.string.series1, R.string.drama),
            Movie(R.drawable.movie, R.string.series2, R.string.action),
            Movie(R.drawable.movie, R.string.series3, R.string.fantasy),
            Movie(R.drawable.movie, R.string.series4, R.string.action),
            Movie(R.drawable.movie, R.string.series5, R.string.action),
            Movie(R.drawable.movie, R.string.series6, R.string.fantasy),
        )
    }
}