package com.example.project_modile_application.data.internet

import com.example.project_modile_application.data.MovieResponse
import com.example.project_modile_application.domain.dataclasses.MoviesData
import com.example.project_modile_application.domain.dataclasses.StaffData
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface KinoPoiskApi {
    @GET("api/v2.2/films")
    suspend fun getMovies(
        @Query("order") order: String = "NUM_VOTE",
        @Query("type") type: String = "FILM",
        @Query("ratingFrom") ratingFrom: Int = 8,
        @Query("ratingTo") ratingTo: Int = 10,
        @Query("yearFrom") yearFrom: Int = 2010,
        @Query("yearTo") yearTo: Int = 2100,
        @Query("page") page: Int = 1,
        @Header("X-API-KEY") apiKey: String = "d7d69928-01dc-42bc-8984-2c259a708259"
    ): Response<MovieResponse>

    @Headers("X-API-KEY: d7d69928-01dc-42bc-8984-2c259a708259")
    @GET("api/v2.2/films/{id}")
    suspend fun getFilmById(
        @Path("id") filmId: Int
    ): MoviesData

    @Headers("X-API-KEY: d7d69928-01dc-42bc-8984-2c259a708259")
    @GET("/api/v1/staff")
    suspend fun getActors(
        @Query("filmId") filmId: Int,
    ): List<StaffData>}

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://kinopoiskapiunofficial.tech/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val apiService: KinoPoiskApi = retrofit.create(KinoPoiskApi::class.java)
