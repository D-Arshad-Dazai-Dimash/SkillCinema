package com.example.project_modile_application.domain

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.project_modile_application.data.Categories

class SharedViewModel : ViewModel() {
    val category = mutableStateOf<Categories>(Categories.Popular)

    fun updateCategory(category: Categories) {
        this.category.value = category
    }
}