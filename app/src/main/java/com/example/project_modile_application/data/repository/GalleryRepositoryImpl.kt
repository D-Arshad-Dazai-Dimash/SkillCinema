package com.example.project_modile_application.data.repository

import com.example.project_modile_application.data.internet.apiService
import com.example.project_modile_application.domain.dataclasses.Images
import com.example.project_modile_application.domain.repository.GalleryRepository

class GalleryRepositoryImpl: GalleryRepository {
    override suspend fun getImagesById(id: Int): Images {
        return apiService.getImagesById(id)
    }

}