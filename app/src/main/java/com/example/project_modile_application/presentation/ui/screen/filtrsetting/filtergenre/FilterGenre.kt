package com.example.project_modile_application.presentation.ui.screen.filtrsetting.filtergenre

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.project_modile_application.domain.dataclasses.searchPage.Country
import com.example.project_modile_application.domain.dataclasses.searchPage.Genre
import com.example.project_modile_application.domain.viewModels.SearchPageParametersViewModel
import com.example.project_modile_application.presentation.navigation.Screen
import com.example.project_modile_application.presentation.ui.screen.filtrsetting.components.CountrySelectionScreen
import com.example.project_modile_application.presentation.ui.screen.searchPageParameters.components.NavBar
import com.example.project_modile_application.presentation.ui.screen.searchPageParameters.components.state.SearchPageParametersIntent

@Preview(showBackground = true)
@Composable
fun FilterGenreScreen(navController: NavController = rememberNavController()) {
    val parentEntry = try {
        navController.getBackStackEntry(Screen.SearchPageParameters.route)
    } catch (e: IllegalArgumentException) {
        null
    }

    val sharedViewModel: SearchPageParametersViewModel = if (parentEntry != null) {
        viewModel(parentEntry)
    } else {
        viewModel()
    }
    val genreList by sharedViewModel.genres.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal =  26.dp)
    ) {
//        TopInfo("Жанр",50)
        NavBar(navController, "Жанр")
        Log.d("IN COMPOSABLE", genreList.toString())
        CountrySelectionScreen("жанр", genreList) { item ->
            sharedViewModel.sendIntent(SearchPageParametersIntent.SetGenre(item as Genre))
        }
    }


}