package com.example.project_modile_application.domain.dataclasses

data class StaffData(
    val staffId: Int,
    val nameRu: String?,
    val nameEn: String?,
    val description: String?,
    val posterUrl: String?,
    val professionText: String,
    val professionKey: String
)