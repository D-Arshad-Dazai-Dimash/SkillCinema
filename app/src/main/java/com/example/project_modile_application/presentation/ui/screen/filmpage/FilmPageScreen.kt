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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.GraphicsLayerScope
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.text.font.FontWeight.Companion.W700
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.project_modile_application.R
import com.example.project_modile_application.domain.dataclasses.Movie
import com.example.project_modile_application.domain.dataclasses.MoviesData
import com.example.project_modile_application.domain.dataclasses.StaffData
import com.example.project_modile_application.domain.viewModels.MovieDetailViewModel
import com.example.project_modile_application.presentation.ui.screen.filmpage.components.DetailMovieItem
import com.example.project_modile_application.presentation.ui.screen.filmpage.components.StuffListShow

@Composable
fun FilmPageScreen(
    navController: NavController,
    movieDetailViewModel: MovieDetailViewModel = viewModel(),
    actorViewModel: MovieDetailViewModel = viewModel()
) {
    val movieState by movieDetailViewModel.stateMovie.collectAsState()
    val actorState by actorViewModel.actorsState.collectAsState()

    if (movieState.isLoading) {
        CircularProgressIndicator()
    } else if (movieState.error.isNotBlank()) {
        Text(
            text = movieState.error,
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
                        val movie = movieState.movie
                        if (movie != null) {
                            DetailMovieItem(movie = movie)
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
                        val movie = movieState.movie
                        if (movie != null) {
                            movieDescription(movie)
                        }

                        if (actorState.isLoading) {
                            CircularProgressIndicator()
                        } else if (actorState.error.isNotBlank()) {
                            Text(
                                text = actorState.error,
                            )
                        } else {
                            val stuffAll = actorState.actor
                            val actors = mutableListOf<StaffData>()
                            val stuff = mutableListOf<StaffData>()

                            for(actor in stuffAll){
                                if(actor.professionKey == "ACTOR"){
                                    actors.add(actor)
                                }else{
                                    stuff.add(actor)
                                }
                            }
                            if (stuffAll != null){
                                StuffListShow(actors, "В фильме снимались" , 4, navController)
                                StuffListShow(stuff, "Над фильмом работали" , 2, navController)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun movieDescription(movie: MoviesData){
    Column(
        modifier = Modifier.padding(vertical = 40.dp)
    ) {
        Text(text = movie.shortDescription,
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 22.sp,
                fontWeight = W700,
            )
        )
        Text(text = movie.description,
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 22.sp,
                fontWeight = W400,
            )
        )
    }
}
