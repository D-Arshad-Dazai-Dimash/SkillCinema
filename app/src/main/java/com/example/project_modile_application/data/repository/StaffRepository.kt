package com.example.project_modile_application.data.repository

import com.example.project_modile_application.data.internet.apiService
import com.example.project_modile_application.domain.dataclasses.StaffData
import com.example.project_modile_application.domain.dataclasses.StaffDataWithFilms
import com.example.project_modile_application.domain.repository.StaffRepository

class StaffRepositoryImplementation : StaffRepository {
    override suspend fun getStaffDetailsByID(id: Int): StaffDataWithFilms {
        return apiService.getStaffWIthFilmsByID(id)
    }
}