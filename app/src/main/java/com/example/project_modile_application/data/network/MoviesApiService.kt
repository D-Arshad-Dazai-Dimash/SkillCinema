package com.example.project_modile_application.data.network

import com.example.project_modile_application.data.model.GenresList
import com.example.project_modile_application.data.model.MoviesList
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

// Define the base URL and your API key
private const val BASE_URL = "https://kinopoiskapiunofficial.tech/"
private const val API_KEY = "d7d69928-01dc-42bc-8984-2c259a708259"

// Create the Interceptor to add the API Key to each request
val apiKeyInterceptor = Interceptor { chain ->
    val original: Request = chain.request()
    val request = original.newBuilder()
        .header("X-API-KEY", API_KEY)
        .header("Content-Type", "application/json")
        .method(original.method, original.body)
        .build()
    chain.proceed(request)
}

// Create OkHttpClient with the interceptor
val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(apiKeyInterceptor)
    .build()

// Build the Retrofit instance
private val retrofit = Retrofit.Builder()
    .client(okHttpClient)
    .baseUrl(BASE_URL)
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .build()
interface MoviesApiService {
    @GET("api/v2.2/films/collections")
    fun getMoviesList(): MoviesList

    @GET("api/v2.2/films/filters")
    fun getGenresList(): GenresList

    @GET("api/v2.2/films?genres={genreID}")
    fun getMoviesByGenre(@Path("genreID") genreId: Int): MoviesList

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