package com.example.project_modile_application.data

import com.example.project_modile_application.R
import com.example.project_modile_application.model.Movie

class DataSource {
    fun loadPremieres() : List<Movie> {
        return listOf<Movie> (
            Movie(R.drawable.image_premiere1, R.string.premiere_movie1, R.string.horror),
            Movie(R.drawable.image_premiere2, R.string.premiere_movie2, R.string.action),
            Movie(R.drawable.image_premiere3, R.string.premiere_movie3, R.string.drama),
            Movie(R.drawable.image_premiere4, R.string.premiere_movie4, R.string.horror),
            Movie(R.drawable.image_premiere5, R.string.premiere_movie5, R.string.drama),
            Movie(R.drawable.image_premiere6, R.string.premiere_movie6, R.string.animation),
            Movie(R.drawable.image_premiere7, R.string.premiere_movie7, R.string.drama),
            Movie(R.drawable.image_premiere8, R.string.premiere_movie8, R.string.animation)
        )
    }
    fun loadPopularMovies() : List<Movie> {
        return listOf<Movie> (
            Movie(R.drawable.image_popular1, R.string.popular_movie1, R.string.drama),
            Movie(R.drawable.image_popular2, R.string.popular_movie2, R.string.fantasy),
            Movie(R.drawable.image_popular3, R.string.popular_movie3, R.string.action),
            Movie(R.drawable.image_popular4, R.string.popular_movie4, R.string.animation),
            Movie(R.drawable.image_popular5, R.string.popular_movie5, R.string.action),
            Movie(R.drawable.image_popular6, R.string.popular_movie6, R.string.horror),
            Movie(R.drawable.image_popular7, R.string.popular_movie7, R.string.action),
            Movie(R.drawable.image_popular8, R.string.popular_movie8, R.string.fantasy)
        )
    }
    fun loadActionMovies() : List<Movie> {
        return listOf<Movie> (
            Movie(R.drawable.image_action1, R.string.action_movie1, R.string.action),
            Movie(R.drawable.image_action2, R.string.action_movie2, R.string.action),
            Movie(R.drawable.image_action3, R.string.action_movie3, R.string.action),
            Movie(R.drawable.image_action4, R.string.action_movie4, R.string.action),
            Movie(R.drawable.image_action5, R.string.action_movie5, R.string.action),
            Movie(R.drawable.image_action6, R.string.action_movie6, R.string.action)
        )
    }
    fun loadDramaMovies() : List<Movie> {
        return listOf<Movie> (
            Movie(R.drawable.image_premiere5, R.string.drama_movie1, R.string.drama),
            Movie(R.drawable.image_drama2, R.string.drama_movie2, R.string.drama),
            Movie(R.drawable.image_popular1, R.string.drama_movie3, R.string.drama),
            Movie(R.drawable.image_drama4, R.string.drama_movie4, R.string.drama),
            Movie(R.drawable.image_popular5, R.string.drama_movie5, R.string.drama),
            Movie(R.drawable.image_drama6, R.string.drama_movie6, R.string.drama)
        )
    }
    fun loadSeries() : List<Movie> {
        return listOf<Movie> (
            Movie(R.drawable.image_series1, R.string.series1, R.string.drama),
            Movie(R.drawable.image_series2, R.string.series2, R.string.action),
            Movie(R.drawable.image_series3, R.string.series3, R.string.fantasy),
            Movie(R.drawable.image_series4, R.string.series4, R.string.action),
            Movie(R.drawable.image_series5, R.string.series5, R.string.action),
            Movie(R.drawable.image_series6, R.string.series6, R.string.fantasy),
        )
    }
}