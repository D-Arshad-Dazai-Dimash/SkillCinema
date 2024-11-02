package com.example.project_modile_application.domain

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_modile_application.data.PosterData
import com.example.project_modile_application.data.internet.KinoPoiskApi
import kotlinx.coroutines.launch

class HomeViewModel(private val apiService: KinoPoiskApi) : ViewModel() {
    val premiers = mutableStateOf<List<PosterData>>(emptyList())
    val popular = mutableStateOf<List<PosterData>>(emptyList())
    val top250 = mutableStateOf<List<PosterData>>(emptyList())

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
                PosterData(
                    title = it.nameRu ?: "Unknown",
                    image = it.posterUrl ?: "",
                    genres = it.genres?.map { genre -> genre.genre } ?: emptyList(),
                    countries = it.countries?.map { country -> country.country } ?: emptyList()
                )
            } ?: emptyList()
            Log.d("MoviesViewModel", "Loaded movies for $category: ${moviesList.size} items") // Отладка
            Log.d("MoviesViewModel", moviesList.toString()) // Полный вывод
            when (category) {
                "premiers" -> premiers.value = moviesList
                "popular" -> popular.value = moviesList
                "top250" -> top250.value = moviesList
            }
        }
    }
}
