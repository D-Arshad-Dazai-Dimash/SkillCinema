package com.example.project_modile_application


//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            Project_Modile_ApplicationTheme {
//                LanguageApp()
//            }
//        }
//    }
//}
//
//@Composable
//fun LanguageApp() {
//    val navController = rememberNavController()
//    Scaffold(
//        topBar = { AppTopBar() },
//        bottomBar = { AppBottomNavigation(navController) }
//    ) { innerPadding ->
//        NavigationGraph(navController, Modifier.padding(innerPadding))
//    }
//}

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.project_modile_application.presentation.navigation.MainComposable
import com.example.project_modile_application.presentation.ui.theme.Project_Modile_ApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Project_Modile_ApplicationTheme {
                MainComposable() // Use your main Composable function
            }
        }
    }
}
