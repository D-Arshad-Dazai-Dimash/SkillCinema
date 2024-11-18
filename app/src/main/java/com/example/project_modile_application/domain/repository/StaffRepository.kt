package com.example.project_modile_application.domain.repository

import com.example.project_modile_application.domain.dataclasses.StaffData

interface StaffRepository {
    suspend fun getStaffDetails(id:Int): StaffData
}