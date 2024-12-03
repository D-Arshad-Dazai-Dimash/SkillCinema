package com.example.project_modile_application.domain.viewModels


import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.example.project_modile_application.domain.dataclasses.Categories
import com.example.project_modile_application.domain.dataclasses.Film
import com.example.project_modile_application.domain.dataclasses.Movie
import com.example.project_modile_application.domain.dataclasses.MoviesData

class SharedViewModel : ViewModel() {
    val category = mutableStateOf<Categories>(Categories.Popular)
    val selectedMovie = mutableStateOf<Movie?>(null)
    val selectedActorFilms = mutableStateOf<List<Film>?>(null)
    val selectedActorName = mutableStateOf<String?>(null)

    private val _watchedMovies = mutableStateListOf<MoviesData>()
    val watchedMovies: List<MoviesData> get() = _watchedMovies

    fun isMovieWatched(movie: MoviesData): Boolean {
        return _watchedMovies.any { it.kinopoiskId == movie.kinopoiskId }
    }

    fun toggleWatchedStatus(movie: MoviesData) {
        val existingMovie = _watchedMovies.find { it.kinopoiskId == movie.kinopoiskId }
        if (existingMovie != null) {
            _watchedMovies.remove(existingMovie)
        } else {
            _watchedMovies.add(movie)
        }
    }

    private val _likedMovie = mutableStateListOf<MoviesData>()
    val likedMovie: List<MoviesData> get() = _likedMovie

    fun isMovieLiked(movie: MoviesData): Boolean {
        return _likedMovie.any { it.kinopoiskId == movie.kinopoiskId }
    }

    fun toggleLikedStatus(movie: MoviesData) {
        val existingMovie = _likedMovie.find { it.kinopoiskId == movie.kinopoiskId }
        if (existingMovie != null) {
            _likedMovie.remove(existingMovie)
        } else {
            _likedMovie.add(movie)
        }
    }

    private val _preferMovie = mutableStateListOf<MoviesData>()
    val preferMovie: List<MoviesData> get() = _preferMovie

    fun isMoviePrefer(movie: MoviesData): Boolean {
        return _preferMovie.any { it.kinopoiskId == movie.kinopoiskId }
    }

    fun togglePreferStatus(movie: MoviesData) {
        val existingMovie = _preferMovie.find { it.kinopoiskId == movie.kinopoiskId }
        if (existingMovie != null) {
            _preferMovie.remove(existingMovie)
        } else {
            _preferMovie.add(movie)
        }
    }

    fun updateCategory(category: Categories) {
        this.category.value = category
    }

    fun selectMovie(movie: Movie) {
        selectedMovie.value = movie
    }

    fun selectedFilms(films: List<Film>) {
        selectedActorFilms.value = films
    }

    fun selectedActorName(name: String) {
        selectedActorName.value = name
    }

    private val _collections = mutableStateListOf("Любимые", "Хочу посмотреть", "Русское кино")
    val collections: List<String> get() = _collections

    private val _collectionMovies = mutableStateMapOf<String, MutableList<MoviesData>>()
    val collectionMovies: Map<String, List<MoviesData>> get() = _collectionMovies

    fun addCollection(name: String) {
        if (name.isNotBlank() && !_collections.contains(name)) {
            _collections.add(name)
            _collectionMovies[name] = mutableListOf()
        }
    }

    fun removeCollection(name: String) {
        _collections.remove(name)
        _collectionMovies.remove(name)
    }

    fun addMovieToCollection(collectionName: String, movie: MoviesData) {
        val moviesInCollection = _collectionMovies[collectionName]
        if (moviesInCollection != null && !moviesInCollection.any { it.kinopoiskId == movie.kinopoiskId }) {
            moviesInCollection.add(movie)
        }
    }

    fun removeMovieFromCollection(collectionName: String, movie: MoviesData) {
        val moviesInCollection = _collectionMovies[collectionName]
        moviesInCollection?.removeIf { it.kinopoiskId == movie.kinopoiskId }
    }

    fun isMovieInCollection(collectionName: String, movie: MoviesData): Boolean {
        return _collectionMovies[collectionName]?.any { it.kinopoiskId == movie.kinopoiskId } == true
    }
}