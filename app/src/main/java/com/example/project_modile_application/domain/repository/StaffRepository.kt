package com.example.project_modile_application.domain.repository

import com.example.project_modile_application.domain.dataclasses.StaffData
import com.example.project_modile_application.domain.dataclasses.StaffDataWithFilms

interface StaffRepository {
    suspend fun getStaffDetails(id:Int): StaffData
    suspend fun getStaffDetailsByID(id:Int): StaffDataWithFilms
}