package com.example.project_modile_application.domain.viewModels

import android.content.Intent
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_modile_application.domain.useCases.MovieUseCase
import com.example.project_modile_application.presentation.ui.screen.filmpage.components.FilmIntent
import com.example.project_modile_application.presentation.ui.screen.filmpage.components.FilmPageResult
import com.example.project_modile_application.presentation.ui.screen.filmpage.components.IntentHandler
import com.example.project_modile_application.presentation.ui.screen.filmpage.components.state.FilmPageState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException


class MovieDetailViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
//    private val _stateMovie = MutableStateFlow<FilmPageState>(FilmPageState())
//    val stateMovie: StateFlow<FilmPageState> = _stateMovie
//    private val _stateActors = MutableStateFlow<ActorsState>(ActorsState())
//    val actorsState: StateFlow<ActorsState> = _stateActors
//    private val _stateImages = MutableStateFlow<GaleryState>(GaleryState())
//    val imagesState: StateFlow<GaleryState> = _stateImages
//    private val _stateSimilar = MutableStateFlow<SimilarState>(SimilarState())
//    val similarState: StateFlow<SimilarState> = _stateSimilar

    private val _state = MutableStateFlow(FilmPageState())
    val state: StateFlow<FilmPageState> = _state

    private val intentFlow = MutableSharedFlow<FilmIntent>()

    private val movieUseCase = MovieUseCase()

//    init {
//        val id: Int? = savedStateHandle.get<String>("id")?.toInt()
//        Log.d("id" , id.toString())
//        if (id != null) {
//            getMovieById(id)
//            getActorById(id)
//            getGallery(id)
//            getSimilarMovies(id)
//        }
//    }

    init {
        handleIntents()
    }

    private fun handleIntents() {
        viewModelScope.launch {
            intentFlow.collect { intent ->
                when (intent) {
                    is FilmIntent.LoadMovies -> getMovieById(intent.movieId)
                    is FilmIntent.LoadSimilarMovies -> getSimilarMovies(intent.movieId)
                    is FilmIntent.LoadActors -> getActorById(intent.movieId)
                    is FilmIntent.LoadGallery -> getGallery(intent.movieId)
                }
            }
        }
    }

    fun getMovieById(id: Int) {
        viewModelScope.launch {
//            _stateMovie.value = _stateMovie.value.copy(isLoading = true)
            _state.value = _state.value.copy(isLoading = true)
            try {
                var movie = movieUseCase.getDetailMovie(id)

//                _stateMovie.value = _stateMovie.value.copy(
//                    isLoading = false,
//                    movie = movie
//                )
                _state.value = IntentHandler(_state.value , FilmPageResult.MovieLoaded(movie))
            } catch (e: HttpException) {
//                _stateMovie.value = _stateMovie.value.copy(
//                    isLoading = false,
//                    error = e.localizedMessage ?: "Unexpected error"
//                )
                _state.value = IntentHandler(_state.value,FilmPageResult.Error(e.localizedMessage?: "Unexpected error"))
            }
        }
    }

    fun getActorById(id: Int) {
        viewModelScope.launch {
//            _stateActors.value = _stateActors.value.copy(isLoading = true)
            _state.value = _state.value.copy(isLoading = true)

            try {
                var actors = movieUseCase.getActors(id)

//                _stateActors.value = _stateActors.value.copy(
//                    isLoading = false,
//                    actor = actors
//                )
                _state.value = IntentHandler(_state.value, FilmPageResult.ActorsLoaded(actors))
            } catch (e: HttpException) {
//                _stateActors.value = _stateActors.value.copy(
//                    isLoading = false,
//                    error = e.localizedMessage ?: "Unexpected error"
//                )
                _state.value = IntentHandler(_state.value, FilmPageResult.Error(e.localizedMessage?: "Unexpected error"))
            }
        }
    }


    fun getGallery(id: Int) {
        viewModelScope.launch {
//            _stateImages.value = _stateImages.value.copy(isLoading = true)
            _state.value = _state.value.copy(isLoading = true)

            try {
                var gallery = movieUseCase.getImages(id)

//                _stateImages.value = _stateImages.value.copy(
//                    isLoading = false,
//                    galery = gallery
//                )
                _state.value = IntentHandler(_state.value, FilmPageResult.GaleryLoaded(gallery))
            } catch (e: HttpException) {
//                _stateImages.value = _stateImages.value.copy(
//                    isLoading = false,
//                    error = e.localizedMessage ?: "Unexpected error"
//                )
                _state.value = IntentHandler(_state.value , FilmPageResult.Error(e.localizedMessage?: "Unexpected error"))
            }
        }
    }

    fun getSimilarMovies(id: Int) {
        viewModelScope.launch {
//            _stateSimilar.value = _stateSimilar.value.copy(isLoading = true)
            _state.value = _state.value.copy(isLoading = true)

            try {
                var similarMovies = movieUseCase.getSimilars(id)

//                _stateSimilar.value = _stateSimilar.value.copy(
//                    isLoading = false,
//                    movies = similarMovies
//                )

                _state.value = IntentHandler(_state.value, FilmPageResult.SimilarMoviesLoaded(similarMovies))
            } catch (e: HttpException) {
//                _stateSimilar.value = _stateSimilar.value.copy(
//                    isLoading = false,
//                    error = e.localizedMessage ?: "Unexpected error"
//                )
                _state.value = IntentHandler(_state.value, FilmPageResult.Error(e.localizedMessage?: "Unexpected error"))
            }
        }
    }

    fun sendIntent(intent: FilmIntent) {
        viewModelScope.launch {
            intentFlow.emit(intent)
        }
    }
}
