package com.example.project_modile_application.presentation.ui.screen.profilePage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.project_modile_application.R
import com.example.project_modile_application.data.local.entities.CollectionEntity
import com.example.project_modile_application.domain.viewModels.RoomViewModel
import com.example.project_modile_application.presentation.ui.font.GraphicFontFamily
import com.example.project_modile_application.presentation.ui.screen.profilePage.components.CollectionItem
import com.example.project_modile_application.presentation.ui.screen.profilePage.components.HeaderForWatchedMovies
import com.example.project_modile_application.presentation.ui.screen.profilePage.components.MoviesDataTab
import com.example.project_modile_application.presentation.ui.screen.profilePage.components.NewCollectionButton


@Preview
@Composable
fun PProfileScree() {
    val navController = rememberNavController()
    val roomViewModel = remember { RoomViewModel() }
    ProfileScreen(navController, roomViewModel)
}

@Composable
fun ProfileScreen(navController: NavController, roomViewModel: RoomViewModel) {
    val watchedMovies = roomViewModel.watchedMovies
    val visitedMovies = roomViewModel.visitedMovies
    val collections = roomViewModel.collections

    val defaultCollections = listOf("Нравится", "Хочу посмотреть")
    val allCollections = remember(collections.value) {
        val currentCollections = collections.value.toMutableList()
        defaultCollections.forEach { defaultName ->
            if (currentCollections.none { it.name == defaultName }) {
                currentCollections.add(CollectionEntity(name = defaultName))
            }
        }
        currentCollections
    }

    val rows = allCollections.chunked(2)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(start = 26.dp)
                .verticalScroll(rememberScrollState())
        ) {
            HeaderForWatchedMovies(
                topic = "Просмотрено",
                watchedMovies = watchedMovies,
                onViewAllClick = {},
                modifier = Modifier.padding(top = 96.dp, end = 26.dp)
            )

            if (watchedMovies.value.isNotEmpty()) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(bottom = 36.dp)
                ) {
                    items(watchedMovies.value.take(8).size) { movie ->
                        MoviesDataTab(
                            movie = watchedMovies.value[movie],
                            navController = navController
                        )
                    }
                    item {
                        Box(
                            modifier = Modifier
                                .size(111.dp, 156.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.clear_icon),
                                    contentDescription = "Clear Watched History",
                                    modifier = Modifier
                                        .size(20.dp)
                                        .clickable { roomViewModel.clearMovies()}
                                        .padding(top = 5.dp),
                                    tint =  Color(0xFF6A5ACD)
                                )
                                Text(
                                    text = "Очистить историю",
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.Center,
                                    color = Color(0xFF6A5ACD)
                                )
                            }
                        }
                    }
                }
            } else {
                Text(
                    text = "Нет просмотренных фильмов.",
                    fontWeight = W400,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 36.dp)
                )
            }

            Text(
                text = "Коллекции",
                fontWeight = W600,
                fontFamily = GraphicFontFamily,
                fontSize = 18.sp,
                lineHeight = 19.8.sp
            )

            NewCollectionButton(roomViewModel)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 26.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val movieCounts = remember { mutableStateOf(mapOf<Int, Int>()) }

                rows.forEach { row ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        row.forEach { collection ->
                            LaunchedEffect(collection.id) {
                                roomViewModel.getMovieCountInCollection(collection.id) { count ->
                                    movieCounts.value = movieCounts.value.toMutableMap().apply {
                                        this[collection.id] = count
                                    }
                                }
                            }

                            val count = movieCounts.value[collection.id] ?: 0
                            val isDefaultCollection = collection.name in defaultCollections

                            CollectionItem(
                                collection = collection,
                                painter = painterResource(R.drawable.iconperson),
                                ableToDelete = !isDefaultCollection,
                                movieCount = count,
                                onRemove = {
                                    if (!isDefaultCollection) {
                                        roomViewModel.removeCollection(collection.id)
                                    }
                                }
                            )
                        }
                        if (row.size < 2) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }


            HeaderForWatchedMovies(
                topic = "Вам было интересно",
                watchedMovies = visitedMovies,
                onViewAllClick = {},
                modifier = Modifier.padding(top = 26.dp, end = 26.dp)
            )

            if (visitedMovies.value.isNotEmpty()) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(bottom = 36.dp)
                ) {
                    items(visitedMovies.value.take(8).size) { movie ->
                        MoviesDataTab(
                            movie = visitedMovies.value[movie],
                            navController = navController
                        )
                    }
                    item {
                        Box(
                            modifier = Modifier
                                .size(111.dp, 156.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.clear_icon),
                                    contentDescription = "Clear viewed History",
                                    modifier = Modifier
                                        .size(20.dp)
                                        .clickable { roomViewModel.clearMovies(4)}
                                        .padding(top = 5.dp),
                                    tint =  Color(0xFF6A5ACD)
                                )
                                Text(
                                    text = "Очистить историю",
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.Center,
                                    color = Color(0xFF6A5ACD)
                                )
                            }
                        }
                    }
                }
            } else {
                Text(
                    text = "Нет заинтересованных фильмов.",
                    fontWeight = W400,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 36.dp)
                )
            }
        }
    }
}