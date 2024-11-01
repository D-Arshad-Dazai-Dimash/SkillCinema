package com.example.project_modile_application.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Movie(
    val movieImageResourceId: Int,
    val movieNameResourceId: Int,
    val movieTypeResourceId: Int,
    val movieRatingResourceId: Int
)
