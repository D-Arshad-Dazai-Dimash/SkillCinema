package com.example.project_modile_application.data.network

import com.example.project_modile_application.data.model.GenresList
import com.example.project_modile_application.data.model.MoviesList
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://kinopoiskapiunofficial.tech/"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()
interface MoviesApiService {
    @GET("api/v2.2/films/collections")
    fun getMoviesList(): MoviesList

    @GET("api/v2.2/films/filters")
    fun getGenresList(): GenresList

    @GET("api/v2.2/films?genres={genreID}")
    fun getMoviesByGenre(@Path("genreId") genreId: Int): MoviesList

//    fun loadPremieres() : List<Movie> {
//        return listOf<Movie> (
//            Movie(R.drawable.movie, R.string.premiere_movie1, R.string.horror, R.string.rating),
//            Movie(R.drawable.movie, R.string.premiere_movie2, R.string.action, R.string.rating),
//            Movie(R.drawable.movie, R.string.premiere_movie3, R.string.drama, R.string.rating),
//            Movie(R.drawable.movie, R.string.premiere_movie4, R.string.horror, R.string.rating),
//            Movie(R.drawable.movie, R.string.premiere_movie5, R.string.drama, R.string.rating),
//            Movie(R.drawable.movie, R.string.premiere_movie6, R.string.animation, R.string.rating),
//            Movie(R.drawable.movie, R.string.premiere_movie7, R.string.drama, R.string.rating),
//            Movie(R.drawable.movie, R.string.premiere_movie8, R.string.animation, R.string.rating)
//        )
//    }
//    fun loadPopularMovies() : List<Movie> {
//        return listOf<Movie> (
//            Movie(R.drawable.movie, R.string.popular_movie1, R.string.drama, R.string.rating),
//            Movie(R.drawable.movie, R.string.popular_movie2, R.string.fantasy, R.string.rating),
//            Movie(R.drawable.movie, R.string.popular_movie3, R.string.action, R.string.rating),
//            Movie(R.drawable.movie, R.string.popular_movie4, R.string.animation, R.string.rating),
//            Movie(R.drawable.movie, R.string.popular_movie5, R.string.action, R.string.rating),
//            Movie(R.drawable.movie, R.string.popular_movie6, R.string.horror, R.string.rating),
//            Movie(R.drawable.movie, R.string.popular_movie7, R.string.action, R.string.rating),
//            Movie(R.drawable.movie, R.string.popular_movie8, R.string.fantasy, R.string.rating)
//        )
//    }
//    fun loadActionMovies() : List<Movie> {
//        return listOf<Movie> (
//            Movie(R.drawable.movie, R.string.action_movie1, R.string.action, R.string.rating),
//            Movie(R.drawable.movie, R.string.action_movie2, R.string.action, R.string.rating),
//            Movie(R.drawable.movie, R.string.action_movie3, R.string.action, R.string.rating),
//            Movie(R.drawable.movie, R.string.action_movie4, R.string.action, R.string.rating),
//            Movie(R.drawable.movie, R.string.action_movie5, R.string.action, R.string.rating),
//            Movie(R.drawable.movie, R.string.action_movie6, R.string.action, R.string.rating)
//        )
//    }
//    fun loadDramaMovies() : List<Movie> {
//        return listOf<Movie> (
//            Movie(R.drawable.movie, R.string.drama_movie1, R.string.drama, R.string.rating),
//            Movie(R.drawable.movie, R.string.drama_movie2, R.string.drama, R.string.rating),
//            Movie(R.drawable.movie, R.string.drama_movie3, R.string.drama, R.string.rating),
//            Movie(R.drawable.movie, R.string.drama_movie4, R.string.drama, R.string.rating),
//            Movie(R.drawable.movie, R.string.drama_movie5, R.string.drama, R.string.rating),
//            Movie(R.drawable.movie, R.string.drama_movie6, R.string.drama, R.string.rating)
//        )
//    }
//    fun loadSeries() : List<Movie> {
//        return listOf<Movie> (
//            Movie(R.drawable.movie, R.string.series1, R.string.drama, R.string.rating),
//            Movie(R.drawable.movie, R.string.series2, R.string.action, R.string.rating),
//            Movie(R.drawable.movie, R.string.series3, R.string.fantasy, R.string.rating),
//            Movie(R.drawable.movie, R.string.series4, R.string.action, R.string.rating),
//            Movie(R.drawable.movie, R.string.series5, R.string.action, R.string.rating),
//            Movie(R.drawable.movie, R.string.series6, R.string.fantasy, R.string.rating),
//        )
//    }
}
object MoviesApi {
    val retrofitService : MoviesApiService by lazy {
        retrofit.create(MoviesApiService::class.java)
    }
}