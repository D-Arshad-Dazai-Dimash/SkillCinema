package com.example.project_modile_application.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.project_modile_application.data.local.dao.CollectionDao
import com.example.project_modile_application.data.local.dao.MovieDao
import com.example.project_modile_application.data.local.entities.CollectionEntity
import com.example.project_modile_application.data.local.entities.CollectionMovieEntity
import com.example.project_modile_application.data.local.entities.MovieEntity

@Database(
    entities = [MovieEntity::class, CollectionEntity::class, CollectionMovieEntity::class],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun collectionDao(): CollectionDao
}

