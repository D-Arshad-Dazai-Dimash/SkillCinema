package com.example.project_modile_application.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "collection_movies",
    foreignKeys = [
        ForeignKey(
            entity = CollectionEntity::class,
            parentColumns = ["id"],
            childColumns = ["collectionId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = ["kinopoiskId"],
            childColumns = ["kinopoiskId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["collectionId"]), Index(value = ["kinopoiskId"])]
)
data class CollectionMovieEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val collectionId: Int,
    val kinopoiskId: Int
)
