package com.example.project_modile_application.domain.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_modile_application.domain.useCases.GalleryUseCase
import com.example.project_modile_application.presentation.ui.screen.galleryPage.components.state.GalleryIntent
import com.example.project_modile_application.presentation.ui.screen.galleryPage.components.state.GalleryState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GalleryViewModel : ViewModel() {
    private val _state = MutableStateFlow<GalleryState>(GalleryState.Loading)
    val state = _state.asStateFlow()

    private val intentFlow = MutableSharedFlow<GalleryIntent>()

    private val galleryUseCase = GalleryUseCase()

    init {
        handleIntents()
    }

    private fun handleIntents() {
        viewModelScope.launch {
            intentFlow.collect { intent ->
                Log.d("A", "Intent collected")

                when (intent) {
                    is GalleryIntent.LoadGalleryIntent -> loadGallery(intent.id)
                }

            }
        }
    }

    fun sendIntent(intent: GalleryIntent) {
        viewModelScope.launch {
            Log.d("A", "Intent sent")
            intentFlow.emit(intent)
        }
    }

    private suspend fun loadGallery(id: Int) {
        _state.value = GalleryState.Loading
        try {
            Log.d("A", "Loading")
            val gallery = galleryUseCase.getImages(id)
            _state.value = GalleryState.Success(gallery)
            Log.d("A size", gallery.items.size.toString())

        } catch (_: Exception) {
            _state.value = GalleryState.Error("Failed to load gallery")
        }
    }
}