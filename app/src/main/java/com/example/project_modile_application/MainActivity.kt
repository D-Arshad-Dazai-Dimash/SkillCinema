package com.example.project_modile_application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.project_modile_application.navigation.BottomNavigation
import com.example.project_modile_application.navigation.OnBoarding
import com.example.project_modile_application.ui.screen.onboarding.OnBoardingScreen
import com.example.project_modile_application.ui.theme.Project_Modile_ApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Project_Modile_ApplicationTheme {

                OnBoardingScreen()
            }
        }
    }
}
