package com.example.project_modile_application.data

sealed class Categories(category: String) {
    data object Premieres : Categories("Премьеры")
    data object Popular : Categories("Популярное")
    data object Top250 : Categories("Топ - 250")
}