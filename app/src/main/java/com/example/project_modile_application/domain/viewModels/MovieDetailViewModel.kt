package com.example.project_modile_application.domain.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_modile_application.domain.useCases.MovieUseCase
import com.example.project_modile_application.presentation.ui.screen.filmpage.components.state.ActorsState
import com.example.project_modile_application.presentation.ui.screen.filmpage.components.state.FilmPageState
import com.example.project_modile_application.presentation.ui.screen.filmpage.components.state.GaleryState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MovieDetailViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val _stateMovie = MutableStateFlow<FilmPageState>(FilmPageState())
    val stateMovie: StateFlow<FilmPageState> = _stateMovie
    private val _stateActors = MutableStateFlow<ActorsState>(ActorsState())
    val actorsState: StateFlow<ActorsState> = _stateActors
    private val _stateImages = MutableStateFlow<GaleryState>(GaleryState())
    val imagesState: StateFlow<GaleryState> = _stateImages

    private val movieUseCase = MovieUseCase()

    init{
        val id: Int? = savedStateHandle.get<String>("id")?.toInt()
        if(id!=null){
            getMovieById(id)
            getActorById(id)
            getGallery(id)
        }
    }

    fun getMovieById(id: Int){
        viewModelScope.launch {
            _stateMovie.value = _stateMovie.value.copy(isLoading = true)

            try{
                var movie = movieUseCase.getDetailMovie(id)

                _stateMovie.value = _stateMovie.value.copy(
                    isLoading = false,
                    movie = movie
                )
            }catch (e: HttpException){
                _stateMovie.value = _stateMovie.value.copy(
                    isLoading = false,
                    error = e.localizedMessage?: "Unexpected error"
                )
            }
        }
    }

    fun getActorById(id: Int){
        viewModelScope.launch {
            _stateActors.value = _stateActors.value.copy(isLoading = true)

            try {
                var actors = movieUseCase.getActors(id)

                _stateActors.value = _stateActors.value.copy(
                    isLoading = false,
                    actor = actors
                )
            } catch (e: HttpException) {
                _stateActors.value = _stateActors.value.copy(
                    isLoading = false,
                    error = e.localizedMessage ?: "An unexpected error occurred"
                )
            }
        }
    }


    fun getGallery(id: Int){
        viewModelScope.launch {
            _stateImages.value = _stateImages.value.copy(isLoading = true)

            try {
                var gallery = movieUseCase.getImages(id)

                _stateImages.value = _stateImages.value.copy(
                    isLoading = false,
                    galery = gallery
                )
            } catch (e: HttpException) {
                _stateImages.value = _stateImages.value.copy(
                    isLoading = false,
                    error = e.localizedMessage ?: "An unexpected error occurred"
                )
            }
        }
    }

}
