package com.example.project_modile_application.utils

import androidx.room.TypeConverter
import com.example.project_modile_application.domain.dataclasses.Country
import com.example.project_modile_application.domain.dataclasses.Genre
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromGenreList(value: List<Genre>): String = Gson().toJson(value)

    @TypeConverter
    fun toGenreList(value: String): List<Genre> =
        Gson().fromJson(value, object : TypeToken<List<Genre>>() {}.type)

    @TypeConverter
    fun fromCountryList(value: List<Country>): String = Gson().toJson(value)

    @TypeConverter
    fun toCountryList(value: String): List<Country> =
        Gson().fromJson(value, object : TypeToken<List<Country>>() {}.type)
}
