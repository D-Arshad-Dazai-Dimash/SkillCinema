package com.example.project_modile_application.presentation.ui.screen.galleryPage.components.state

sealed class GalleryIntent {
    data class LoadGalleryIntent(val id: Int) :GalleryIntent()
}