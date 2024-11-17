package com.example.project_modile_application.presentation.ui.screen.filmpage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.project_modile_application.domain.MovieDetailViewModel
import com.example.project_modile_application.domain.SharedViewModel

@Composable
fun FilmPageScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    movieDetailViewModel: MovieDetailViewModel
) {
    val selectedMovie = sharedViewModel.selectedMovie.value
    if (selectedMovie != null) {
        movieDetailViewModel.loadMovieDetails(selectedMovie.kinopoiskId)
    }
    val movieDetails = movieDetailViewModel.movieDetails.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        if (selectedMovie != null) {
            AsyncImage(
                model = selectedMovie.posterUrl,
                contentDescription = selectedMovie.title,
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (selectedMovie != null) {
            Text(text = selectedMovie.title, fontSize = 24.sp)
        }

        movieDetails?.let { details ->
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Год: ${details.year}")
            Text(text = "Рейтинг: ${details.ratingKinopoisk}")
            Text(text = "Страны: ${details.countries.joinToString { it.country }}")
            Text(text = "Жанры: ${details.genres.joinToString { it.genre }}")
            Text(text = "Описание: ${details.description}")
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Добавьте галерею, сотрудников и другие секции
    }
//    val selectedMovie = sharedViewModel.selectedMovie.value
//    val actors = sharedViewModel.actors.value
//
//    val genresText = selectedMovie?.genres?.joinToString(", ") { it.genre } ?: "Жанры не указаны"
//    val countriesText =
//        selectedMovie?.countries?.joinToString(", ") { it.country } ?: "Страны не указаны"
//    val movieLength =
//        if (selectedMovie?.filmLength != 0) "${selectedMovie?.filmLength} мин" else "Длительность не указана"
//    val ageLimit = selectedMovie?.ratingAgeLimits ?: "Возрастное ограничение не указано"
//
//
//    LaunchedEffect(selectedMovie?.kinopoiskId) {
//        selectedMovie?.let { sharedViewModel.fetchActors(selectedMovie.kinopoiskId) }
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .verticalScroll(rememberScrollState())
//    ) {
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .size(400.dp),
//            ) {
//
//            Log.d("FilmPageScreen", "coverUrl: ${selectedMovie?.shortDescription}")
//
//            AsyncImage(
//                model = selectedMovie?.coverUrl.takeIf { !it.isNullOrEmpty() }
//                    ?: selectedMovie?.posterUrl,
//                contentDescription = selectedMovie?.nameRu,
//                modifier = Modifier
//                    .fillMaxSize()
//                    .fillMaxWidth()
//            )
//
//            Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
//                modifier = Modifier.fillMaxSize()
//            ) {
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(top = 42.dp, bottom = 142.dp)
//                ) {
//                    Icon(
//                        painter = painterResource(R.drawable.icon_arrow_back),
//                        contentDescription = "",
//                        modifier = Modifier
//                            .padding(start = 34.75.dp)
//                            .size(9.5.dp, 9.5.dp)
//                    )
//                }
//                AsyncImage(
//                    model = selectedMovie?.logoUrl,
//                    contentDescription = "",
//                    modifier = Modifier.size(160.dp, 40.dp)
//                )
//                var color = Color(0xFFB5B5C9)
//                if (selectedMovie?.coverUrl.isNullOrEmpty()) {
//                    color = Color.White
//                }
//                PutText("${selectedMovie?.ratingKinopoisk ?: "N/A"}", color)
//                PutText("${selectedMovie?.year}, $genresText", color)
//                PutText("$countriesText , $movieLength , $ageLimit", color)
//            }
//
//        }
//        Column(
//            verticalArrangement = Arrangement.spacedBy(40.dp),
//            modifier = Modifier.padding(start = 26.dp, top = 40.dp),
//
//            ) {
//            Text(
//                text = selectedMovie?.description.toString(),
//                fontFamily = GraphicFontFamily,
//                fontWeight = FontWeight.W700,
//                lineHeight = 22.sp,
//                fontSize = 16.sp,
//                color = Color.Black
//            )
//            Text(
//                text = selectedMovie?.shortDescription.toString(),
//                fontFamily = GraphicFontFamily,
//                fontWeight = FontWeight.W700,
//                lineHeight = 22.sp,
//                fontSize = 16.sp
//            )
//
//
//            Row(
//                horizontalArrangement = Arrangement.SpaceBetween,
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text(
//                    text = "В фильме снимались",
//                    fontFamily = GraphicFontFamily,
//                    fontSize = 18.sp,
//                    lineHeight = 19.8.sp,
//                    fontWeight = W600
//                )
//
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    modifier = Modifier.padding(end = 26.dp)
//                ) {
//                    Text(
//                        text = actors.size.toString(),
//                        fontFamily = GraphicFontFamily,
//                        fontWeight = W600,
//                        fontSize = 16.sp,
//                        color = Color.Black
//                    )
//                    Icon(
//                        painter = painterResource(R.drawable.right_arrow_icon),
//                        contentDescription = "",
//                        modifier = Modifier.size(18.dp, 18.dp),
//                    )
//                }
//                if (actors.isNotEmpty()) {
//                    ActorsList(actors)
//                } else {
//                Text(
//                    text = "No actors found",
//                    fontFamily = GraphicFontFamily,
//                    fontSize = 16.sp,
//                    color = Color.Gray
//                )
//            }
//            }
//        }
//    }
//}
//
//@Composable
//fun PutText(text: String, color: Color) {
//    Text(
//        text = text,
//        fontSize = 12.sp,
//        fontWeight = FontWeight.W400,
//        lineHeight = 13.2.sp,
//        textAlign = TextAlign.Center,
//        fontFamily = GraphicFontFamily,
//        color = color,
//        modifier = Modifier.padding(4.dp)
//    )
//}
//
//@Composable
//fun ActorsList(actors: List<StaffData>) {
//    LazyHorizontalGrid(
//        rows = GridCells.Fixed(10),
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(200.dp)
//            .padding(16.dp, 8.dp),
//        horizontalArrangement = Arrangement.spacedBy(12.dp),
//        verticalArrangement = Arrangement.spacedBy(12.dp)
//    ) {
//        items(actors) { actor ->
//            ActorCard(actor)
//        }
//    }
//}
//@Composable
//fun ActorCard(actor: StaffData) {
//    Log.d("FilmPageScreen", "Rendering actor: ${actor.nameRu ?: actor.nameEn}")
//
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier
//            .size(120.dp)
//            .padding(8.dp)
//    ) {
//        AsyncImage(
//            model = actor.posterUrl,
//            contentDescription = actor.nameRu ?: actor.nameEn ?: "Actor Image",
//            modifier = Modifier
//                .size(100.dp)
//                .align(Alignment.CenterHorizontally)
//        )
//        Text(
//            text = actor.nameRu ?: actor.nameEn ?: "Неизвестный актер",
//            fontSize = 12.sp,
//            fontWeight = FontWeight.Bold,
//            textAlign = TextAlign.Center,
//            color = Color.Black,
//            modifier = Modifier.fillMaxWidth()
//        )
//    }
}

