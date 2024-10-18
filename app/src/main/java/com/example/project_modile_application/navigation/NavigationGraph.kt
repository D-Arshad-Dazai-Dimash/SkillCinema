package com.example.project_modile_application.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) { Home() }
        composable(Screen.Search.route) { Search() }
        composable(Screen.Profile.route) { Profile() }
    }
}
