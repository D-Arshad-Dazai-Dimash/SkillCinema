package com.example.project_modile_application.presentation.ui.screen.filmpage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
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
import com.example.project_modile_application.domain.viewModels.SharedViewModel
import com.example.project_modile_application.presentation.ui.screen.filmpage.components.FilmIntent
import com.example.project_modile_application.presentation.ui.screen.filmpage.components.items.DetailMovieItem
import com.example.project_modile_application.presentation.ui.screen.filmpage.components.items.GalleryListItem
import com.example.project_modile_application.presentation.ui.screen.filmpage.components.items.SimilarMoviesShow
import com.example.project_modile_application.presentation.ui.screen.filmpage.components.items.StuffListShow

@Composable
fun FilmPageScreen(
    navController: NavController,
    movie: Movie,
    sharedViewModel: SharedViewModel,
    movieDetailViewModel: MovieDetailViewModel = viewModel(),

//    actorViewModel: MovieDetailViewModel = viewModel(),
//    imageViewModel: MovieDetailViewModel = viewModel(),
//    similarViewModel: MovieDetailViewModel = viewModel()
) {
//    val movieState by movieDetailViewModel.stateMovie.collectAsState()
//    val actorState by actorViewModel.actorsState.collectAsState()
//    val imageState by imageViewModel.imagesState.collectAsState()
//    val similarState by similarViewModel.similarState.collectAsState()

    val state by movieDetailViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        movieDetailViewModel.sendIntent(FilmIntent.LoadMovies(movie.kinopoiskId))
        movieDetailViewModel.sendIntent(FilmIntent.LoadActors(movie.kinopoiskId))
        movieDetailViewModel.sendIntent(FilmIntent.LoadSimilarMovies(movie.kinopoiskId))
        movieDetailViewModel.sendIntent(FilmIntent.LoadGallery(movie.kinopoiskId))
    }

    if (state.isLoading) {
        CircularProgressIndicator()
    } else {
        LazyColumn(
            modifier = Modifier
                .padding(bottom = 100.dp)
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .height(400.dp)
                        .fillMaxWidth()
                ) {
                    val movie = state.movie
                    if (movie != null) {
                        DetailMovieItem(movie = movie , sharedViewModel)
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
                    val movie = state.movie
                    if (movie != null) {
                        MovieDescription(movie)
                    }

                    if (state.isLoading) {
                        CircularProgressIndicator()
                    } else {
                        val stuffAll = state.actors
                        val actors = mutableListOf<StaffData>()
                        val stuff = mutableListOf<StaffData>()

                        if (stuffAll != null) {
                            for (actor in stuffAll) {
                                if (actor.professionKey == "ACTOR") {
                                    actors.add(actor)
                                } else {
                                    stuff.add(actor)
                                }
                            }
                        }
                        if (stuffAll != null) {
                            StuffListShow(actors, "В фильме снимались", 4, navController)
                            StuffListShow(stuff, "Над фильмом работали", 2, navController)
                        }
                    }
                    if (state.isLoading) {
                        CircularProgressIndicator()
                    } else {
                        val images = state.galery
                        if (images != null) {
                            GalleryListItem(images.items, navController)
                        }
                    }
                    if (state.isLoading) {
                        CircularProgressIndicator()
                    } else {
                        val similarMovies = state.similarMovies
                        if (similarMovies != null) {
                            SimilarMoviesShow(similarMovies.items, navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MovieDescription(movie: MoviesData) {
    Column(
        modifier = Modifier.padding(vertical = 40.dp)
    ) {
        Text(
            text = movie.shortDescription,
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 22.sp,
                fontWeight = W700,
            )
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Text(
            text = movie.description,
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 22.sp,
                fontWeight = W400,
            )
        )
    }
}