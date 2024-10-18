package com.example.project_modile_application.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.project_modile_application.ui.screen.onboarding.OnBoardingScreen


@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "onboarding") {
        composable(Screen.Home.route) { Home() }
        composable(Screen.Search.route) { Search() }
        composable(Screen.Profile.route) { Profile() }
        composable("main") { BottomNavigation()  }
        composable("onboarding") { OnBoardingScreen()  }
    }
}

