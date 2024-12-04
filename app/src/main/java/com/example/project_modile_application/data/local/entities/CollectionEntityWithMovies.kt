package com.example.project_modile_application.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class CollectionEntityWithMovies(
    @Embedded val collection: CollectionEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "collectionId"
    )
    val movies: List<CollectionMovieEntity>
)
