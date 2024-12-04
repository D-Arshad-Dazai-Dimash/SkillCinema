package com.example.project_modile_application.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.project_modile_application.data.local.entities.CollectionEntity
import com.example.project_modile_application.data.local.entities.CollectionEntityWithMovies
import com.example.project_modile_application.data.local.entities.CollectionMovieEntity

@Dao
interface CollectionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCollection(collection: CollectionEntity): Long

    @Insert
    suspend fun insertCollectionMovies(collectionMovies: List<CollectionMovieEntity>)

    @Query("SELECT * FROM collections")
    suspend fun getCollections(): List<CollectionEntity>

    @Query("SELECT * FROM collection_movies WHERE collectionId = :collectionId")
    suspend fun getMoviesInCollection(collectionId: Int): List<CollectionMovieEntity>

    @Query("DELETE FROM collections WHERE id = :collectionId")
    suspend fun deleteCollection(collectionId: Int)

    @Query("DELETE FROM collection_movies WHERE collectionId = :collectionId")
    suspend fun deleteMoviesInCollection(collectionId: Int)

    @Transaction
    @Query("SELECT * FROM collections WHERE id = :collectionId")
    suspend fun getCollectionWithMovies(collectionId: Int): CollectionEntityWithMovies


}
