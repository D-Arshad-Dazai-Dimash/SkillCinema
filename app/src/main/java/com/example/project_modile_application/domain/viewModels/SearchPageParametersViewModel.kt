package com.example.project_modile_application.domain.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_modile_application.presentation.ui.screen.searchPageParameters.components.state.SearchPageParametersIntent
import com.example.project_modile_application.presentation.ui.screen.searchPageParameters.components.state.SearchPageParametersState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchPageParametersViewModel : ViewModel() {
    private val _state = MutableStateFlow(SearchPageParametersState())
    val state = _state.asStateFlow()

    private val intentFlow = MutableSharedFlow<SearchPageParametersIntent>()


    init {
        handleIntents()
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

}