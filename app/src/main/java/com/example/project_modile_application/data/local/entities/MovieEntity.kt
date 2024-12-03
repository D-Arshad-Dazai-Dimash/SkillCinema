package com.example.project_modile_application.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val kinopoiskId: Int,
    val title: String,
    val image: String,
    val year: Int,
    val genres: String,
    val countries: String,
    val posterUrl: String,
    val isWatched: Boolean = false
)



