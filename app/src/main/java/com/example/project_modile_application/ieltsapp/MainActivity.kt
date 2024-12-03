//package com.example.project_modile_application.ieltsapp
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Scaffold
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.navigation.compose.rememberNavController
//import com.example.project_modile_application.ieltsapp.ui.NavigationGraph
//import com.example.project_modile_application.ieltsapp.ui.components.AppBottomNavigation
//import com.example.project_modile_application.ieltsapp.ui.components.AppTopBar
//import com.example.project_modile_application.presentation.ui.theme.Project_Modile_ApplicationTheme
//
//
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