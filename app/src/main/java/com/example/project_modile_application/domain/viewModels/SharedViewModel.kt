package com.example.project_modile_application.domain.viewModels


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.project_modile_application.data.Categories
import com.example.project_modile_application.domain.dataclasses.Film
import com.example.project_modile_application.domain.dataclasses.Movie

class SharedViewModel : ViewModel() {
    val category = mutableStateOf<Categories>(Categories.Popular)
    val selectedMovie = mutableStateOf<Movie?>(null)
    val selectedActorFilms = mutableStateOf<List<Film>?>(null)
    val selectedActorName = mutableStateOf<String?>(null)

    fun updateCategory(category: Categories) {
        this.category.value = category
    }

    fun selectMovie(movie: Movie) {
        selectedMovie.value = movie
    }

    fun selectedFilms(films: List<Film>) {
        selectedActorFilms.value = films
    }

    fun selectedActorName(name: String) {
        selectedActorName.value = name
    }
}