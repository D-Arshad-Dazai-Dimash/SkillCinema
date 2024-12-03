package com.example.project_modile_application.domain.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface movieDao {
    @Query("SELECT * FROM movies WHERE collectionName = :collectionName")
    fun getMoviesByCollection(collectionName: String): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Delete
    suspend fun deleteMovie(movie: MovieEntity)
}
