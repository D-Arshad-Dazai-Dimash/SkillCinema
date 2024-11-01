package com.example.project_modile_application.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_modile_application.data.MovieRepository
import com.example.project_modile_application.data.model.GenresList
import com.example.project_modile_application.data.model.MoviesList
import com.example.project_modile_application.data.network.MoviesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface MoviesUiState {
    data class Success(val moviesList: MoviesList) : MoviesUiState
    object Error : MoviesUiState
    object Loading : MoviesUiState
}

sealed interface GenresUiState {
    data class Success(val genresList: GenresList) : GenresUiState
    object Error : GenresUiState
    object Loading : GenresUiState
}

class MoviesViewModel : ViewModel() {
//    var moviesUiState: MoviesUiState by mutableStateOf(MoviesUiState.Loading)
//        private set

//    var genresUiState: GenresUiState by mutableStateOf(GenresUiState.Loading)
//        private set
    private val _genresUiState = MutableStateFlow<GenresUiState>(GenresUiState.Loading)
    val genresUiState: StateFlow<GenresUiState> = _genresUiState

//    private fun getMoviesList() {
//        viewModelScope.launch {
//            try {
//                val listResult = MoviesApi.retrofitService.getMoviesList()
//                moviesUiState = MoviesUiState.Success(listResult)
//            } catch (e: Exception) {
//                moviesUiState = MoviesUiState.Error
//            }
//        }
//    }
    init{
        getGenreList()
    }
    private fun getGenreList() {
        // Simulate loading data
        // You would normally call your repository or API here
        viewModelScope.launch {
            try {
                val genres = MoviesApi.retrofitService.getGenresList() // Fetch genres from your data source
                _genresUiState.value = GenresUiState.Success(genres)
            } catch (e: Exception) {
                _genresUiState.value = GenresUiState.Error
            }
        }
    }


//    private fun getGenreList() {
//        viewModelScope.launch {
//            try {
//                val listResult = MoviesApi.retrofitService.getGenresList()
//                genresUiState = GenresUiState.Success(listResult)
//            } catch (e: Exception) {
//                genresUiState = GenresUiState.Error
//            }
//        }
//    }

//    private fun getMoviesByGenre(genreId: Int) {
//        viewModelScope.launch {
//            try {
//                val listResult = MoviesApi.retrofitService.getMoviesByGenre(genreId)
//                moviesUiState = MoviesUiState.Success(listResult)
//            } catch (e: Exception) {
//                moviesUiState = MoviesUiState.Error
//            }
//        }
//    }
}

//class HomeViewModel(private val movieRepository: MovieRepository): ViewModel() {
//    var moviesUiState: MoviesUiState by mutableStateOf(MoviesUiState.Loading)
//        private set
//    private fun getMoviesByGenre(genreId: Int) {
//        viewModelScope.launch {
//            try {
//                val listResult = MoviesApi.retrofitService.getMoviesByGenre(genreId)
//                moviesUiState = MoviesUiState.Success(listResult)
//            } catch (e: Exception) {
//                moviesUiState = MoviesUiState.Error
//            }
//        }
//    }
//}

class HomeViewModel : ViewModel() {

    private val _moviesUiState = MutableStateFlow<MoviesUiState>(MoviesUiState.Loading)
    val moviesUiState: StateFlow<MoviesUiState> = _moviesUiState

    fun loadMovies(genreId: Int) {
        viewModelScope.launch {
            _moviesUiState.value = MoviesUiState.Loading
            try {
                val movies = MoviesApi.retrofitService.getMoviesByGenre(genreId)// Fetch movies based on genre ID
                _moviesUiState.value = MoviesUiState.Success(movies)
            } catch (e: Exception) {
                _moviesUiState.value = MoviesUiState.Error
            }
        }
    }
}
