package com.example.project_modile_application.domain.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_modile_application.domain.dataclasses.searchPage.Country
import com.example.project_modile_application.domain.dataclasses.searchPage.Genre
import com.example.project_modile_application.domain.useCases.SearchParametersUseCase
import com.example.project_modile_application.presentation.ui.screen.searchPageParameters.components.state.SearchPageParametersIntent
import com.example.project_modile_application.presentation.ui.screen.searchPageParameters.components.state.SearchPageParametersState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchPageParametersViewModel : ViewModel() {
    private val searchParametersUseCase = SearchParametersUseCase()

    private val _state = MutableStateFlow(SearchPageParametersState())
    val state = _state.asStateFlow()
    private val _countries: MutableStateFlow<List<Country>> = MutableStateFlow(listOf())
    val countries = _countries.asStateFlow()
    private val _genres: MutableStateFlow<List<Genre>> = MutableStateFlow(listOf())
    val genres = _genres.asStateFlow()


    private val intentFlow = MutableSharedFlow<SearchPageParametersIntent>()

    init {
        handleIntents()
        loadCountries()
        loadGenres()
    }

    private fun handleIntents() {
        viewModelScope.launch {
            intentFlow.collect { intent ->
                when (intent) {
                    is SearchPageParametersIntent.SetCountry -> {
                        _state.value = _state.value.copy(countries = intent.country)
                    }

                    is SearchPageParametersIntent.SetGenre -> {
                        _state.value = _state.value.copy(genres = intent.genre)
                        Log.d("NEW GENRE", intent.genre.toString())
                    }

                    is SearchPageParametersIntent.SetRating -> {
                        _state.value = _state.value.copy(
                            ratingFrom = intent.rating.start.toDouble(),
                            ratingTo = intent.rating.endInclusive.toDouble()
                        )
                    }

                    is SearchPageParametersIntent.SetYear -> {
                        _state.value = _state.value.copy(
                            yearFrom = intent.year.first,
                            yearTo = intent.year.last
                        )
                        Log.d(
                            "NEW YEAR",
                            intent.year.first.toString() + " " + intent.year.last.toString()
                        )

                    }

                    is SearchPageParametersIntent.SetOrder -> {
                        _state.value = _state.value.copy(
                            order = intent.order.apiValue
                        )
                    }

                    is SearchPageParametersIntent.SetType -> {
                        _state.value = _state.value.copy(
                            order = intent.type.apiValue
                        )
                    }

                    SearchPageParametersIntent.SetWatched -> {
                        _state.value = _state.value.copy(
                            watched = !_state.value.watched
                        )
                    }
                }
            }
        }
    }

    fun sendIntent(intent: SearchPageParametersIntent) {
        viewModelScope.launch {
            intentFlow.emit(intent)
        }
    }

    private fun loadCountries() {
        viewModelScope.launch {
            _countries.value = searchParametersUseCase.getCountries()
            Log.d("COUNTRIES", _countries.value.toString())
        }
    }

    private fun loadGenres() {
        viewModelScope.launch {
            _genres.value = searchParametersUseCase.getGenres()
        }
    }

}