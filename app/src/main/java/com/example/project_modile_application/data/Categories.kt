package com.example.project_modile_application.data

sealed class Categories(val category: String) {
    data object Premieres : Categories("Премьеры")
    data object Popular : Categories("Популярное")
    data object Top250 : Categories("Топ - 250")
    companion object {
        val allCategories = listOf(Premieres, Popular, Top250)
    }
}