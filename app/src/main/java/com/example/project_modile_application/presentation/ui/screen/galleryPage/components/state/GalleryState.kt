package com.example.project_modile_application.presentation.ui.screen.galleryPage.components.state

import com.example.project_modile_application.domain.dataclasses.Images

sealed class GalleryState {
    data object Loading : GalleryState()
    data class Success(val images: Images? = null) : GalleryState()
    data class Error(val message: String = ""): GalleryState()
}