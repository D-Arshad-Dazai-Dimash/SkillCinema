package com.example.project_modile_application.domain.repository

import com.example.project_modile_application.domain.dataclasses.Images

interface GalleryRepository {
    suspend fun getImagesById(id: Int): Images
}