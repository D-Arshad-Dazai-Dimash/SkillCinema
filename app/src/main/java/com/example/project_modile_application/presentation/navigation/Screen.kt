package com.example.project_modile_application.presentation.navigation


sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Search : Screen("search")
    data object Profile : Screen("profile")
    data object Onboarding : Screen("onboarding")
    data object ListingPage : Screen("listingPage")
    data object FilmPage : Screen("MovieData/{id}")
    data object GalleryPage : Screen("gallery")
    data object SearchPageParameters : Screen("SearchPageParameters")
}
