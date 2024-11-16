package com.example.project_modile_application.domain


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.project_modile_application.data.Categories
import com.example.project_modile_application.data.MoviesData

class SharedViewModel : ViewModel() {
    val category = mutableStateOf<Categories>(Categories.Popular)

    fun updateCategory(category: Categories) {
        this.category.value = category
    }

    private val _selectedMovie = mutableStateOf<MoviesData?>(null)
    val selectedMovie: State<MoviesData?> = _selectedMovie

    fun selectMovie(movie: MoviesData) {
        _selectedMovie.value = movie
    }
}