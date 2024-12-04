package com.example.project_modile_application.presentation.ui.screen.searchPageParameters

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.project_modile_application.R
import com.example.project_modile_application.domain.dataclasses.searchPage.Order
import com.example.project_modile_application.domain.dataclasses.searchPage.Type
import com.example.project_modile_application.domain.viewModels.SearchPageParametersViewModel
import com.example.project_modile_application.presentation.navigation.Screen
import com.example.project_modile_application.presentation.ui.screen.filtrsetting.filtergenre.FilterGenreScreen
import com.example.project_modile_application.presentation.ui.screen.searchPageParameters.components.FilterParameter
import com.example.project_modile_application.presentation.ui.screen.searchPageParameters.components.FilterTab
import com.example.project_modile_application.presentation.ui.screen.searchPageParameters.components.NavBar
import com.example.project_modile_application.presentation.ui.screen.searchPageParameters.components.RatingSlider
import com.example.project_modile_application.presentation.ui.screen.searchPageParameters.components.WatchedFilter
import com.example.project_modile_application.presentation.ui.screen.searchPageParameters.components.state.SearchPageParametersIntent
import java.util.Calendar

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SearchPageParametersPrev() {
    SearchPageParameters()
}

@Composable
fun SearchPageParameters(
    navController: NavController = rememberNavController(),
    viewModel: SearchPageParametersViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        NavBar(navController, "Настройки поиска")
        FilterTab(listOf(Type.All, Type.Film, Type.Series), title = "Показывать") { type ->
            viewModel.sendIntent(SearchPageParametersIntent.SetType(type as Type))
        }
        FilterParameter("Страна", state.countries?.country ?: "Любая") {
            navController.navigate(Screen.FilterCountry.route)
        }
        HorizontalDivider()
        FilterParameter("Жанр", state.genres?.genre ?: "Любой") {
            navController.navigate(Screen.FilterGenre.route)
        }
        HorizontalDivider()
        FilterParameter(
            "Год",
            if (state.yearFrom == 1900 && state.yearTo == Calendar.getInstance().get(
                    Calendar.YEAR
                )
            ) "Любой" else "с ${state.yearFrom} до ${state.yearTo}"
        ) { navController.navigate(Screen.FilterPeriod.route) }
        HorizontalDivider()
        RatingSlider { rating ->
            viewModel.sendIntent(SearchPageParametersIntent.SetRating(rating))
        }
        HorizontalDivider()
        FilterTab(
            listOf(Order.Date, Order.Popularity, Order.Rating),
            title = "Сортировать"
        ) { order ->
            viewModel.sendIntent(SearchPageParametersIntent.SetOrder(order as Order))
        }
        HorizontalDivider(modifier = Modifier.padding(top = 16.dp))
        WatchedFilter(watched = state.watched) {
            viewModel.sendIntent(SearchPageParametersIntent.SetWatched)
        }
    }
}
