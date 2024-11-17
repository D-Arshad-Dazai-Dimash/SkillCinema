package com.example.project_modile_application.domain


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_modile_application.data.Categories
import com.example.project_modile_application.data.MoviesData
import com.example.project_modile_application.data.StaffData
import com.example.project_modile_application.data.internet.apiService
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {
    val category = mutableStateOf<Categories>(Categories.Popular)

    fun updateCategory(category: Categories) {
        this.category.value = category
    }

    private val _selectedMovie = mutableStateOf<Movie?>(null)
    val selectedMovie: State<Movie?> = _selectedMovie

    fun selectMovie(movie: Movie) {
        _selectedMovie.value = movie
    }

    private val _actors = mutableStateOf<List<StaffData>>(emptyList())
    val actors: State<List<StaffData>> = _actors

    fun fetchActors(filmId: Int) {
        val launch = viewModelScope.launch {
            try {
                val response = apiService.getStaffByFilmId(filmId)
                if (response.isSuccessful) {
                    val actorsList = response.body()?.filter { it.professionKey == "ACTOR" } ?: emptyList()
                    Log.d("SharedViewModel", "Fetched actors: ${actorsList.size}")
                    _actors.value = actorsList
                } else {
                    Log.e("SharedViewModel", "Error fetching actors: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("SharedViewModel", "Error fetching actors: ${e.message}")
            }
        }
    }

}