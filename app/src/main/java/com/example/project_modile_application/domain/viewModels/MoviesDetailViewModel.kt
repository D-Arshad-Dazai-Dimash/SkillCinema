package com.example.project_modile_application.domain.viewModels

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_modile_application.data.internet.apiService
import com.example.project_modile_application.domain.useCases.MovieUseCase
import com.example.project_modile_application.domain.useCases.StaffUseCase
import com.example.project_modile_application.presentation.ui.screen.actorpage.state.ActorDataState
import com.example.project_modile_application.presentation.ui.screen.actorpage.state.MovieDetailState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MoviesDetailViewModel(savedStateHandle: SavedStateHandle): ViewModel() {
    private val _stateMovies = MutableStateFlow<MovieDetailState>(MovieDetailState())
    val stateMovies: StateFlow<MovieDetailState> = _stateMovies

    private val movieUseCase = MovieUseCase()

    init {
        val id: Int? = savedStateHandle.get<String>("id")?.toInt()
        Log.d("id" , id.toString())
        if (id != null) {
            getMovieDataByID(id)
        }
    }
    fun getMovieDataByID(id: Int) {
        viewModelScope.launch {
            _stateMovies.value = _stateMovies.value.copy(isLoading = true)

            try {
                val movie = movieUseCase.getDetailMovie(id)

                _stateMovies.value = _stateMovies.value.copy(
                    isLoading = false,
                    movie = movie
                )
            } catch (e: HttpException) {
                _stateMovies.value = _stateMovies.value.copy(
                    isLoading = false,
                    error = e.localizedMessage ?: "Unexpected error"
                )
            }
        }
    }
}