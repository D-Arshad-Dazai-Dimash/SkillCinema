package com.example.project_modile_application.domain.viewModels


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_modile_application.App
import com.example.project_modile_application.data.local.entities.CollectionEntity
import com.example.project_modile_application.data.local.entities.CollectionMovieEntity
import com.example.project_modile_application.data.local.entities.MovieEntity
import com.example.project_modile_application.domain.dataclasses.Categories
import com.example.project_modile_application.domain.dataclasses.Film
import com.example.project_modile_application.domain.dataclasses.Movie
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {
    val category = mutableStateOf<Categories>(Categories.Popular)
    val selectedMovie = mutableStateOf<Movie?>(null)
    val selectedActorFilms = mutableStateOf<List<Film>?>(null)
    val selectedActorName = mutableStateOf<String?>(null)

    private val movieDao = App.database.movieDao()
    private val collectionDao = App.database.collectionDao()

    val watchedMovies = mutableStateOf<List<MovieEntity>>(emptyList())
    val likedMovies = mutableStateOf<List<MovieEntity>>(emptyList())
    val preferredMovies = mutableStateOf<List<MovieEntity>>(emptyList())
    val collections = mutableStateOf<List<CollectionEntity>>(emptyList())

    init {
        fetchWatchedMovies()
        fetchCollections()
        ensureDefaultCollections()
    }

    private fun fetchWatchedMovies() {
        viewModelScope.launch {
            try {
                watchedMovies.value = movieDao.getWatchedMovies()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun fetchCollections() {
        viewModelScope.launch {
            try {
                collections.value = collectionDao.getCollections()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun toggleWatchedStatus(movie: MovieEntity) {
        viewModelScope.launch {
            try {
                if (isMovieWatched(movie)) {
                    movieDao.deleteMovie(movie.kinopoiskId)
                } else {
                    movieDao.insertMovie(movie.copy(isWatched = true))
                }
                fetchWatchedMovies()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun toggleLikedStatus(movie: MovieEntity) {
        viewModelScope.launch {
            try {
                if (isMovieLiked(movie)) {
                    collectionDao.deleteMoviesInCollection(movie.kinopoiskId) // Adjust if you need a specific liked collection
                } else {
                    val likedCollectionId = collections.value.firstOrNull { it.name == "Liked" }?.id
                        ?: collectionDao.insertCollection(CollectionEntity(name = "Liked")).toInt()
                    collectionDao.insertCollectionMovies(
                        listOf(
                            CollectionMovieEntity(
                                collectionId = likedCollectionId,
                                kinopoiskId = movie.kinopoiskId
                            )
                        )
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun togglePreferStatus(movie: MovieEntity) {
        viewModelScope.launch {
            try {
                if (isMoviePrefer(movie)) {
                    collectionDao.deleteMoviesInCollection(movie.kinopoiskId) // Adjust if you need a specific preferred collection
                } else {
                    val preferCollectionId =
                        collections.value.firstOrNull { it.name == "Preferred" }?.id
                            ?: collectionDao.insertCollection(CollectionEntity(name = "Preferred"))
                                .toInt()
                    collectionDao.insertCollectionMovies(
                        listOf(
                            CollectionMovieEntity(
                                collectionId = preferCollectionId,
                                kinopoiskId = movie.kinopoiskId
                            )
                        )
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun isMovieWatched(movie: MovieEntity): Boolean {
        return watchedMovies.value.any { it.kinopoiskId == movie.kinopoiskId }
    }

    fun isMovieLiked(movie: MovieEntity): Boolean {
        return collections.value.any { collection ->
            collection.name == "Liked" && movie.kinopoiskId in likedMovies.value.map { it.kinopoiskId }
        }
    }

    fun isMoviePrefer(movie: MovieEntity): Boolean {
        return collections.value.any { collection ->
            collection.name == "Preferred" && movie.kinopoiskId in preferredMovies.value.map { it.kinopoiskId }
        }
    }

    private fun ensureDefaultCollections() {
        viewModelScope.launch {
            try {
                val existingCollections = collectionDao.getCollections()
                val defaultCollections = listOf(
//                    CollectionEntity(name = "Liked", ableToDelete = false),
//                    CollectionEntity(name = "Preferred", ableToDelete = false) ,
                    CollectionEntity(name = "Liked"),
                    CollectionEntity(name = "Preferred")
                )

                defaultCollections.forEach { defaultCollection ->
                    if (existingCollections.none { it.name == defaultCollection.name }) {
                        collectionDao.insertCollection(defaultCollection)
                    }
                }
                fetchCollections()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    fun addMovie(movie: MovieEntity) {
        viewModelScope.launch {
            try {
                movieDao.insertMovie(movie)
                fetchWatchedMovies()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun removeMovie(movieId: Int) {
        viewModelScope.launch {
            try {
                movieDao.deleteMovie(movieId)
                fetchWatchedMovies()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun addCollection(name: String) {
        viewModelScope.launch {
            if (name.isNotBlank()) {
                try {
                    val collection = CollectionEntity(name = name)
                    collectionDao.insertCollection(collection)
                    fetchCollections()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

//    fun removeCollection(collectionId: Int) {
//        viewModelScope.launch {
//            try {
//                val collection = collections.value.firstOrNull { it.id == collectionId }
//                if (collection != null && collection.ableToDelete) {
//                    collectionDao.deleteCollection(collectionId)
//                    fetchCollections()
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//    }

    fun removeCollection(collectionId: Int) {
        viewModelScope.launch {
            try {
                collectionDao.deleteCollection(collectionId)
                fetchCollections()
            } catch (e: Exception) {
                e.printStackTrace()
            }
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
}
