package com.example.project_modile_application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.project_modile_application.navigation.MainComposable
import com.example.project_modile_application.ui.theme.Project_Modile_ApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Project_Modile_ApplicationTheme {
                MainComposable()
            }
        }
    }
}