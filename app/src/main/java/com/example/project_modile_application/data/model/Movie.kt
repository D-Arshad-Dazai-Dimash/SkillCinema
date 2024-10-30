package com.example.project_modile_application.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Movie(
    @DrawableRes
    val movieImageResourceId: Int,
    @StringRes
    val movieNameResourceId: Int,
    @StringRes
    val movieTypeResourceId: Int,
    @StringRes
    val movieRatingResourceId: Int
)
