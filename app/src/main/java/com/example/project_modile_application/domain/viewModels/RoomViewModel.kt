package com.example.project_modile_application.domain.viewModels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_modile_application.App
import com.example.project_modile_application.data.local.entities.CollectionEntity
import com.example.project_modile_application.data.local.entities.CollectionMovieEntity
import com.example.project_modile_application.data.local.entities.MovieEntity
import kotlinx.coroutines.launch

class RoomViewModel : ViewModel() {
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
                collectionDao.getCollection("Просмотрено")

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchCollections() {
        viewModelScope.launch {
            try {
                collections.value = collectionDao.getCollections().filter { collectionEntity -> !collectionEntity.name.equals("Просмотрено") && !collectionEntity.name.equals("Заинтересовало") }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun clearMovies(collectionId: Int? = null) {
        viewModelScope.launch {
            try {
                if (collectionId == null) {
                    movieDao.deleteAllWatchedMovies()
                    fetchWatchedMovies()
                } else {
                    collectionDao.deleteMoviesInCollection(collectionId)
                    fetchCollections()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getMovieCountInCollection(collectionId: Int, onResult: (Int) -> Unit) {
        viewModelScope.launch {
            try {
                val count = collectionDao.getMoviesInCollection(collectionId).size
                onResult(count)
            } catch (e: Exception) {
                e.printStackTrace()
                onResult(0)
            }
        }
    }

    private fun ensureDefaultCollections() {
        viewModelScope.launch {
            try {
                val existingCollections = collectionDao.getCollections()
                val defaultCollections = listOf(
                    CollectionEntity(name = "Нравится"),
                    CollectionEntity(name = "Хочу посмотреть"),
                    CollectionEntity(name = "Просмотрено"),
                    CollectionEntity(name = "Заинтересовало")
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

    fun addMovieToCollection(movie: MovieEntity, collectionId: Int) {
        viewModelScope.launch {
            try {
                val collectionExists = collectionDao.getCollections().any { it.id == collectionId }
                val movieExists = movieDao.isMovieExists(movie.kinopoiskId)
                if (!movieExists) {
                    movieDao.insertMovie(movie)
                }

                if (collectionExists) {
                    val collectionMovie = CollectionMovieEntity(
                        collectionId = collectionId,
                        kinopoiskId = movie.kinopoiskId
                    )
                    collectionDao.insertCollectionMovies(listOf(collectionMovie))
                } else {
//                    if (!collectionExists) {
//                        throw IllegalStateException("Collection with id $collectionId does not exist")
//                    }
//                    if (!movieExists) {
//                        throw IllegalStateException("Movie with kinopoiskId ${movie.kinopoiskId} does not exist")
//                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    fun removeMovieFromCollection(movie: MovieEntity, collectionId: Int) {
        viewModelScope.launch {
            try {
                collectionDao.deleteMovieFromCollection(collectionId, movie.kinopoiskId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getMovieCollections(movieId: Int, onResult: (List<CollectionEntity>) -> Unit) {
        viewModelScope.launch {
            try {
                val collectionsWithMovie = collectionDao.getCollectionsWithMovie(movieId)
                onResult(collectionsWithMovie)
            } catch (e: Exception) {
                e.printStackTrace()
                onResult(emptyList())
            }
        }
    }

    suspend fun isMovieLiked(movie: MovieEntity): Boolean {
        val likedCollection = collections.value.firstOrNull { it.name == "Нравится" }
        return if (likedCollection != null) {
            collectionDao.isMovieInCollection(movie.kinopoiskId, likedCollection.id)
        } else {
            false
        }
    }


    fun toggleLikedMovie(movie: MovieEntity) {
        viewModelScope.launch {
            try {
                val isMovieInDatabase = movieDao.isMovieExists(movie.kinopoiskId)
                Log.d("IS MOVIE IN DB", isMovieInDatabase.toString())
                if (!isMovieInDatabase) {
                    movieDao.insertMovie(movie)
                }
                val likedCollection = collections.value.firstOrNull { it.name == "Нравится" }
                    ?: run {
                        val newCollection = CollectionEntity(name = "Нравится")
                        val newId = collectionDao.insertCollection(newCollection)
                        fetchCollections()
                        newCollection.copy(id = newId.toInt())
                    }
                Log.d("LIKE", "1 step")

                val isLiked =
                    collectionDao.isMovieInCollection(movie.kinopoiskId, likedCollection.id)

                if (isLiked) {
                    Log.d("LIKE", "del step")

                    collectionDao.deleteMovieFromCollection(likedCollection.id, movie.kinopoiskId)
                } else {
                    Log.d("LIKE", "add step")
                    collectionDao.insertCollectionMovies(
                        listOf(
                            CollectionMovieEntity(
                                collectionId = likedCollection.id,
                                kinopoiskId = movie.kinopoiskId
                            )
                        )
                    )
                }
                fetchCollections()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    suspend fun isMovieWannaSee(movie: MovieEntity): Boolean {
        val wannaSeeCollection = collections.value.firstOrNull { it.name == "Хочу посмотреть" }
        return if (wannaSeeCollection != null) {
            collectionDao.isMovieInCollection(movie.kinopoiskId, wannaSeeCollection.id)
        } else {
            false
        }
    }

    fun toggleWantToWatch(movie: MovieEntity) {
        viewModelScope.launch {
            try {
                val isMovieInDatabase = movieDao.isMovieExists(movie.kinopoiskId)
                Log.d("IS MOVIE IN DB", isMovieInDatabase.toString())
                if (!isMovieInDatabase) {
                    movieDao.insertMovie(movie)
                }
                val wannaSeeCollection =
                    collections.value.firstOrNull { it.name == "Хочу посмотреть" }
                        ?: run {
                            val newCollection = CollectionEntity(name = "Хочу посмотреть")
                            val newId = collectionDao.insertCollection(newCollection)
                            fetchCollections()
                            newCollection.copy(id = newId.toInt())
                        }
                Log.d("WANNA SEE", "1 step")

                val isLiked =
                    collectionDao.isMovieInCollection(movie.kinopoiskId, wannaSeeCollection.id)

                if (isLiked) {
                    Log.d("WANNA SEE", "del step")

                    collectionDao.deleteMovieFromCollection(
                        wannaSeeCollection.id,
                        movie.kinopoiskId
                    )
                } else {
                    Log.d("WANNA SEE", "add step")
                    collectionDao.insertCollectionMovies(
                        listOf(
                            CollectionMovieEntity(
                                collectionId = wannaSeeCollection.id,
                                kinopoiskId = movie.kinopoiskId
                            )
                        )
                    )
                }
                fetchCollections()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    suspend fun isMovieWatched(movie: MovieEntity): Boolean {
        val wannaSeeCollection = collections.value.firstOrNull { it.name == "Посмотрено" }
        return if (wannaSeeCollection != null) {
            collectionDao.isMovieInCollection(movie.kinopoiskId, wannaSeeCollection.id)
        } else {
            false
        }
    }

    fun toggleWatched(movie: MovieEntity) {
        viewModelScope.launch {
            try {
                val isMovieInDatabase = movieDao.isMovieExists(movie.kinopoiskId)
                Log.d("IS MOVIE IN DB", isMovieInDatabase.toString())
                if (!isMovieInDatabase) {
                    movieDao.insertMovie(movie)
                }
                val wannaSeeCollection =
                    collections.value.firstOrNull { it.name == "Просмотрено" }
                        ?: run {
                            val newCollection = CollectionEntity(name = "Просмотрено")
                            val newId = collectionDao.insertCollection(newCollection)
                            fetchCollections()
                            newCollection.copy(id = newId.toInt())
                        }
                Log.d("WANNA SEE", "1 step")

                val isLiked =
                    collectionDao.isMovieInCollection(movie.kinopoiskId, wannaSeeCollection.id)

                if (isLiked) {
                    Log.d("WANNA SEE", "del step")

                    collectionDao.deleteMovieFromCollection(
                        wannaSeeCollection.id,
                        movie.kinopoiskId
                    )
                } else {
                    Log.d("WANNA SEE", "add step")
                    collectionDao.insertCollectionMovies(
                        listOf(
                            CollectionMovieEntity(
                                collectionId = wannaSeeCollection.id,
                                kinopoiskId = movie.kinopoiskId
                            )
                        )
                    )
                }
                fetchCollections()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun visitMovie(movie: MovieEntity) {
        viewModelScope.launch {
            try {
                val isMovieInDatabase = movieDao.isMovieExists(movie.kinopoiskId)
                Log.d("IS MOVIE IN DB", isMovieInDatabase.toString())
                if (!isMovieInDatabase) {
                    movieDao.insertMovie(movie)
                }
                val wannaSeeCollection =
                    collections.value.firstOrNull { it.name == "Заинтересовало" }
                        ?: run {
                            val newCollection = CollectionEntity(name = "Заинтересовало")
                            val newId = collectionDao.insertCollection(newCollection)
                            fetchCollections()
                            newCollection.copy(id = newId.toInt())
                        }
                val isLiked =
                    collectionDao.isMovieInCollection(movie.kinopoiskId, wannaSeeCollection.id)

                if (!isLiked) {
                    Log.d("WANNA SEE", "add step")
                    collectionDao.insertCollectionMovies(
                        listOf(
                            CollectionMovieEntity(
                                collectionId = wannaSeeCollection.id,
                                kinopoiskId = movie.kinopoiskId
                            )
                        )
                    )
                }
                fetchCollections()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


}