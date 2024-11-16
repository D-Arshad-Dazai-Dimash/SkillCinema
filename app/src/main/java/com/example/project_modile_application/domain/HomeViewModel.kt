package com.example.project_modile_application.domain

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_modile_application.data.MoviesData
import com.example.project_modile_application.data.internet.KinoPoiskApi
import kotlinx.coroutines.launch

class HomeViewModel(private val apiService: KinoPoiskApi) : ViewModel() {
    val premiers = mutableStateOf<List<MoviesData>>(emptyList())
    val popular = mutableStateOf<List<MoviesData>>(emptyList())
    val top250 = mutableStateOf<List<MoviesData>>(emptyList())

    init {
        loadMovies("premiers")
        loadMovies("popular")
        loadMovies("top250")
    }

    private fun loadMovies(category: String) {
        viewModelScope.launch {
            val response = when (category) {
                "premiers" -> apiService.getMovies(yearFrom = 2023)
                "popular" -> apiService.getMovies(order = "NUM_VOTE")
                "top250" -> apiService.getMovies(order = "RATING", ratingFrom = 8)
                else -> null
            }
            val moviesList = response?.body()?.items?.map {
                MoviesData(
                    kinopoiskId = it.kinopoiskId ?: 0,
                    title = it.nameRu ?: "Unknown",
                    image = it.posterUrl ?: "",
                    genres = it.genres.map { Genre(it.toString()) } ?: emptyList(),
                    countries = it.countries?.map {Country(it.toString()) } ?: emptyList(),
                    description = it.description ?: "",
                    coverUrl = it.coverUrl ?: "",
                    editorAnnotation = it.editorAnnotation ?: "",
                    filmLength = it.filmLength ?: 0,
                    logoUrl = it.logoUrl?: "",
                    nameEn = it.nameEn ?: "",
                    nameRu = it.nameEn?: "",
                    nameOriginal = it.nameOriginal?: "",
                    posterUrlPreview = it.posterUrlPreview?: "",
                    ratingKinopoisk = it.ratingKinopoisk ?: 0.0,
                    shortDescription = it.shortDescription?: "",
                    slogan = it.slogan?: "",
                    type = it.type?: "",
                    webUrl = it.webUrl?: "",
                    year = it.year?: 0,
                    posterUrl = it.posterUrl?: ""
                )
            } ?: emptyList()
            Log.d("MoviesViewModel", "Loaded movies for $category: ${moviesList.size} items")
            Log.d("MoviesViewModel", moviesList.toString())
            when (category) {
                "premiers" -> premiers.value = moviesList
                "popular" -> popular.value = moviesList
                "top250" -> top250.value = moviesList
            }
        }
    }
}
