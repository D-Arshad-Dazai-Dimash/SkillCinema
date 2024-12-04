package com.example.project_modile_application.presentation.ui.screen.filtrsetting.filtrcountry

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.project_modile_application.domain.dataclasses.searchPage.Country
import com.example.project_modile_application.domain.viewModels.SearchPageParametersViewModel
import com.example.project_modile_application.presentation.navigation.Screen
import com.example.project_modile_application.presentation.ui.screen.filtrsetting.components.CountrySelectionScreen
import com.example.project_modile_application.presentation.ui.screen.searchPageParameters.components.NavBar
import com.example.project_modile_application.presentation.ui.screen.searchPageParameters.components.state.SearchPageParametersIntent

@Preview(showBackground = true)
@Composable
fun FilterCountryScreen(navController: NavController = rememberNavController()) {
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
    val countryList by sharedViewModel.countries.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 26.dp)
    ) {
//        TopInfo("Страна",45)
        NavBar(navController, "Страна")
        CountrySelectionScreen("страну", countryList) { item ->
            sharedViewModel.sendIntent(SearchPageParametersIntent.SetCountry(item as Country))
        }
    }


}