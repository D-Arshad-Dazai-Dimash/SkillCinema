package com.example.project_modile_application.domain.viewModels

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_modile_application.domain.dataclasses.MoviesData
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
                if (staff.films.size > 50) staff.films = staff.films.take(50)
                for (index in 0 until staff.films.size) {
                    val film = staff.films[index]
                    film.posterUrl = movieUseCase.getDetailMovie(film.filmId).posterUrl
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

//    private fun getFilmPosterByID(id: Int): MoviesData? {
//        var movie = movie = movieUseCase.getDetailMovie(id)
//        return movie
//    }
}