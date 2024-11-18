package com.example.project_modile_application.presentation.ui.screen.filmpage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.project_modile_application.R
import com.example.project_modile_application.domain.viewModels.MovieDetailViewModel
import com.example.project_modile_application.presentation.ui.screen.filmpage.components.DetailMovieItem

@Composable
fun FilmPageScreen(
    navController: NavController,
    movieDetailViewModel: MovieDetailViewModel = viewModel()
) {
    val stateMovie by movieDetailViewModel.stateMovie.collectAsState()
    if (stateMovie.isLoading) {
        CircularProgressIndicator(
            modifier = Modifier
        )
    } else if (stateMovie.error.isNotBlank()) {

        Text(
            text = stateMovie.error,
            modifier = Modifier

        )

    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(bottom = 100.dp)
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .height(400.dp)
                            .fillMaxWidth()
                    ) {
                        val mov = stateMovie.movie
                        if (mov != null) {
                            DetailMovieItem(movie = mov)
                        }
                        IconButton(
                            onClick = { navController.popBackStack() },
                            modifier = Modifier
                                .background(Color.Transparent)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.icon_arrow_back),
                                contentDescription = "Back icon",
                            )
                        }

                    }
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 26.dp)
                    ) {
                        val mov = stateMovie.movie
                    }


                }
            }

        }
    }
}
