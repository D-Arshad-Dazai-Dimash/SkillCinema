package com.example.project_modile_application.domain

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_modile_application.data.internet.KinoPoiskApi
import com.example.project_modile_application.data.dataclasses.MoviesData
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val apiService: KinoPoiskApi) : ViewModel() {
    val movieDetails = mutableStateOf<MoviesData?>(null)

    fun loadMovieDetails(movieId: Int) {
        viewModelScope.launch {
            try {
                val response = apiService.getFilmById(movieId)
                movieDetails.value = response
            } catch (e: Exception) {
            }
        }
    }
}
