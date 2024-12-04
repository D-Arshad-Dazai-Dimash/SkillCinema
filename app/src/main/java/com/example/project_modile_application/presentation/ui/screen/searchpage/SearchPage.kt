package com.example.project_modile_application.presentation.ui.screen.searchpage

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.project_modile_application.R
import com.example.project_modile_application.domain.viewModels.SearchViewModel
import com.example.project_modile_application.presentation.navigation.Screen
import com.example.project_modile_application.presentation.ui.screen.UIStateScreens.ErrorUIState
import com.example.project_modile_application.presentation.ui.screen.UIStateScreens.LoadingUIState
import com.example.project_modile_application.presentation.ui.screen.searchpage.items.FilmList
import com.example.project_modile_application.presentation.ui.screen.searchpage.items.FilmRow
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SearchPage(navController: NavController, searchViewModel: SearchViewModel = viewModel()) {
    val searchState by searchViewModel.stateFilms.collectAsState()

    var searchText by remember { mutableStateOf(TextFieldValue("")) }
    var currentSearchText by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(currentSearchText) {
        if (currentSearchText.isNotEmpty() && !searchState.isLoading) {
            delay(300)
            searchViewModel.getFilmsByKeyWord(currentSearchText)
        }
    }


    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(top = 20.dp, start = 10.dp, end = 10.dp)
                .fillMaxWidth()
                .height(50.dp)
                .clip(RoundedCornerShape(40.dp))
                .background(Color.LightGray, RoundedCornerShape(10.dp))
        ) {
            Image(
                painter = painterResource(R.drawable.icon_3),
                contentDescription = "icon",
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp)
            )

            TextField(
                value = searchText,
                onValueChange = {newValue ->
                    searchText = newValue
                    coroutineScope.launch {
                        currentSearchText = newValue.text.trim()
                    }
                },

                placeholder = { Text("Search for a film") },
                modifier = Modifier
                    .weight(5f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(40.dp))
                    .background(Color.Transparent),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                singleLine = true
            )

            Image(
                painter = painterResource(R.drawable.filter),
                contentDescription = "filter",
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 10.dp)
                    .clickable {
                        navController.navigate(Screen.SearchPageParameters.route)
                    }
            )
        }
        Log.d("loading", searchState.isLoading.toString())
        if (searchState.isLoading) {
            LoadingUIState()
        } else if (searchState.error != "") {
            Log.d("error", "error bovatr")
            ErrorUIState(navController,searchState.error)
        } else {
            val films = searchState.films
            if (films != null) {
                if (films.isEmpty()) {
                    LoadingUIState()
                } else {
                    FilmList(modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                        films = films
                    )
                }
            }
            Log.d("films in search page", searchState.films.toString())
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun FilmListPreview() {
//    val sampleMovies = listOf(
//        Movie(nameRu = "Movie 1", title = "This is a description for movie 1.", posterUrl = R.drawable.movie.toString()),
//        Movie(nameRu = "Movie 2", title = "This is a description for movie 2.", R.drawable.sample_poster),
//        Movie(nameRu = "Movie 3", title ="This is a description for movie 3.", R.drawable.sample_poster)
//    )
//    FilmList(movies = sampleMovies)
//}