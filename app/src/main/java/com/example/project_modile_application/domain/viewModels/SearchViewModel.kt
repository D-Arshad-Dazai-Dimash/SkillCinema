package com.example.project_modile_application.domain.viewModels

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_modile_application.domain.dataclasses.Film
import com.example.project_modile_application.domain.useCases.MovieUseCase
import com.example.project_modile_application.presentation.ui.screen.actorpage.state.MovieDetailState
import com.example.project_modile_application.presentation.ui.screen.searchpage.state.SearchDetailState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SearchViewModel() : ViewModel() {
    private val _stateFilms = MutableStateFlow<SearchDetailState>(SearchDetailState())
    val stateFilms: StateFlow<SearchDetailState> = _stateFilms

    private val movieUseCase = MovieUseCase()

    fun getFilmsByKeyWord(keyWord: String) {
        Log.d("keyword", keyWord)
        viewModelScope.launch {
            _stateFilms.value = _stateFilms.value.copy(isLoading = true)

            try {
                val films = movieUseCase.getFilmsByKeyWord(keyWord).films

                _stateFilms.value = _stateFilms.value.copy(
                    isLoading = false,
                    films = films
                )

                Log.d("films", stateFilms.value.films.toString())


            } catch (e: HttpException) {
                _stateFilms.value = _stateFilms.value.copy(
                    isLoading = false,
                    error = e.localizedMessage ?: "Unexpected error"
                )
            }
        }
    }
}