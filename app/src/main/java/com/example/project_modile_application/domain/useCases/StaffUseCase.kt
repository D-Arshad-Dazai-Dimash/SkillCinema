package com.example.project_modile_application.domain.useCases

import com.example.project_modile_application.data.repository.StaffRepositoryImplementation
import com.example.project_modile_application.domain.dataclasses.StaffDataWithFilms

class StaffUseCase {
    private val staffRepository = StaffRepositoryImplementation()

    suspend fun getStaffDetailsByID(id: Int): StaffDataWithFilms {
        return staffRepository.getStaffDetailsByID(id)
    }
}