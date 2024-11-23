package com.example.project_modile_application.domain.useCases;

import com.example.project_modile_application.data.repository.GalleryRepositoryImpl
import com.example.project_modile_application.domain.dataclasses.Images

class GalleryUseCase {
    private val galleryRepository = GalleryRepositoryImpl()
    suspend fun getImages(
        id: Int
    ): Images {
        return galleryRepository.getImagesById(id)
    }
}
