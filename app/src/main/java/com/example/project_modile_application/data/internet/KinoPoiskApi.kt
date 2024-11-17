package com.example.project_modile_application.data.internet

import com.example.project_modile_application.data.MovieResponse
import com.example.project_modile_application.data.MoviesData
import com.example.project_modile_application.data.StaffData
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface KinoPoiskApi {
    @GET("api/v2.2/films")
    suspend fun getMovies(
        @Query("order") order: String = "NUM_VOTE",
        @Query("type") type: String = "FILM",
        @Query("ratingFrom") ratingFrom: Int = 5,
        @Query("ratingTo") ratingTo: Int = 10,
        @Query("yearFrom") yearFrom: Int = 1900,
        @Query("yearTo") yearTo: Int = 2100,
        @Query("page") page: Int = 1,
        @Header("X-API-KEY") apiKey: String = "ce1c91b2-5c47-4503-a848-f7581f7a276c"
    ): Response<MovieResponse>

    @GET("api/v2.2/films/{id}")
    suspend fun getFilmById(
        @Path("id") filmId: Int
    ): MoviesData

    @GET("staff")
    suspend fun getStaffByFilmId(@Query("filmId") filmId: Int): Response<List<StaffData>>
}

val retrofit = Retrofit.Builder()
    .baseUrl("https://kinopoiskapiunofficial.tech/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val apiService: KinoPoiskApi = retrofit.create(KinoPoiskApi::class.java)
