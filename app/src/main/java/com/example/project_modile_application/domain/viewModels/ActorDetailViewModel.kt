package com.example.project_modile_application.domain.viewModels

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_modile_application.domain.dataclasses.Movie
import com.example.project_modile_application.domain.dataclasses.MoviesData
import com.example.project_modile_application.domain.useCases.MovieUseCase
import com.example.project_modile_application.domain.useCases.StaffUseCase
import com.example.project_modile_application.presentation.ui.screen.actorpage.state.ActorDataState
import com.example.project_modile_application.presentation.ui.screen.actorpage.state.MovieDetailState
import com.example.project_modile_application.presentation.ui.screen.filmpage.components.state.FilmPageState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ActorDetailViewModel(savedStateHandle: SavedStateHandle): ViewModel() {
    private val _stateStaff = MutableStateFlow<ActorDataState>(ActorDataState())
    val stateStaff: StateFlow<ActorDataState> = _stateStaff

    private val _stateMovie = MutableStateFlow<MovieDetailState>(MovieDetailState())
    val stateMovie: StateFlow<MovieDetailState> = _stateMovie

    private val staffUseCase = StaffUseCase()
    private val movieUseCase = MovieUseCase()

    init {
        val id: Int? = savedStateHandle.get<String>("id")?.toInt()
        Log.d("id" , id.toString())
        if (id != null) {
            getStaffDataByID(id)
        }
    }

    fun getStaffDataByID(id: Int) {
        viewModelScope.launch {
            _stateStaff.value = _stateStaff.value.copy(isLoading = true)

            try {
                var staff = staffUseCase.getStaffDetailsByID(id)

                _stateStaff.value = _stateStaff.value.copy(
                    isLoading = false,
                    staff = staff
                )
            } catch (e: HttpException) {
                _stateStaff.value = _stateStaff.value.copy(
                    isLoading = true,
                    error = e.localizedMessage ?: "Unexpected error"
                )
            }
        }
    }

    fun getMovieDataByID(id: Int) {
        viewModelScope.launch {
            _stateMovie.value = _stateMovie.value.copy(isLoading = true)

            try {
                val movie = movieUseCase.getDetailMovie(id)

                _stateMovie.value = _stateMovie.value.copy(
                    isLoading = false,
                    movie = movie
                )
            } catch (e: HttpException) {
                _stateStaff.value = _stateStaff.value.copy(
                    isLoading = true,
                    error = e.localizedMessage ?: "Unexpected error"
                )
            }
        }
    }
}