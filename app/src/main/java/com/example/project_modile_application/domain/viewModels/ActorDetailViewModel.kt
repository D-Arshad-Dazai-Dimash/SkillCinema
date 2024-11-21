package com.example.project_modile_application.domain.viewModels

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_modile_application.domain.useCases.MovieUseCase
import com.example.project_modile_application.domain.useCases.StaffUseCase
import com.example.project_modile_application.presentation.ui.screen.actorpage.state.ActorDataState
import com.example.project_modile_application.presentation.ui.screen.actorpage.state.MovieDetailState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ActorDetailViewModel(savedStateHandle: SavedStateHandle): ViewModel() {
    private val _stateStaff = MutableStateFlow<ActorDataState>(ActorDataState())
    val stateStaff: StateFlow<ActorDataState> = _stateStaff

    private val _stateFilm = MutableStateFlow<MovieDetailState>(MovieDetailState())
    val stateFilm: StateFlow<MovieDetailState> = _stateFilm

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
                staff.films.forEach { film ->
                    getFilmPosterByID(film.filmId)
                    film.posterUrl = _stateFilm.value.movie?.posterUrl
                    film.posterUrl?.let { Log.d("poster_url"+film.nameRu, it) }
                }

                _stateStaff.value = _stateStaff.value.copy(
                    isLoading = false,
                    staff = staff
                )
            } catch (e: HttpException) {
                _stateStaff.value = _stateStaff.value.copy(
                    isLoading = false,
                    error = e.localizedMessage ?: "Unexpected error"
                )
            }
        }
    }

    private fun getFilmPosterByID(id: Int) {
        viewModelScope.launch {
            _stateFilm.value = _stateFilm.value.copy(isLoading = true)

            try {
                val movie = movieUseCase.getDetailMovie(id)

                _stateFilm.value = _stateFilm.value.copy(
                    isLoading = false,
                    movie = movie
                )
            } catch (e: HttpException) {
                _stateFilm.value = _stateFilm.value.copy(
                    isLoading = false,
                    error = e.localizedMessage ?: "Unexpected error"
                )
            }
        }
    }
}