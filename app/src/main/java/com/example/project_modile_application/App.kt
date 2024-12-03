package com.example.project_modile_application

import android.app.Application
import androidx.room.Room
import com.example.project_modile_application.data.local.dao.MIGRATION_1_2
import com.example.project_modile_application.data.local.database.AppDatabase

class App : Application() {
    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).addMigrations(MIGRATION_1_2)
            .fallbackToDestructiveMigration() // Optional: Clears data if migration fails
            .build()
    }
}
