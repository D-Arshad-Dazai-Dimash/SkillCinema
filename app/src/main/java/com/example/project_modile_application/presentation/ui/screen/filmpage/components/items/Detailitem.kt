package com.example.project_modile_application.presentation.ui.screen.filmpage.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.project_modile_application.R
import com.example.project_modile_application.domain.dataclasses.MoviesData
import com.example.project_modile_application.domain.viewModels.SharedViewModel

@Composable
fun DetailMovieItem(movie: MoviesData, sharedViewModel: SharedViewModel) {

    val isWatched = sharedViewModel.isMovieWatched(movie)


    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            model = movie.coverUrl,
            contentDescription = "Movie poster",
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.7f),
                            Color(0xFF1B1B1B).copy(alpha = 0.7f),
                            Color(0xFF1B1B1B).copy(alpha = 1f)
                        ),
                    )
                )
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                modifier = Modifier.padding(bottom = 10.dp),
                text = movie.nameOriginal.uppercase(),
                style = TextStyle(
                    fontSize = 33.sp,
                    fontWeight = FontWeight(700),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    letterSpacing = 4.sp
                ),
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = movie.ratingKinopoisk.toString() + " ", style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFFB5B5C9),
                        textAlign = TextAlign.Center,
                    )
                )
                Text(
                    text = movie.nameRu, style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFFB5B5C9),
                        textAlign = TextAlign.Center,
                    )
                )

            }

            var genres = ""
            for (genre in movie.genres) {
                genres += genre.genre + ", "
            }
            Text(
                text = movie.year.toString() + ", " + genres.substring(
                    0, genres.length - 2
                ), style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFFB5B5C9),
                ), modifier = Modifier.padding(3.dp)
            )
            var lengthOfMovie = ""

            if (movie.filmLength / 60 >= 0) {
                lengthOfMovie += (movie.filmLength / 60).toString() + " ч"
                if (movie.filmLength / 60 > 0) {
                    lengthOfMovie += " " + (movie.filmLength % 60).toString() + " мин"
                }
            } else {
                lengthOfMovie += movie.filmLength.toString() + " мин"
            }

            val ageLimit = movie.ratingAgeLimits
            if (ageLimit.isNotEmpty()) {
                lengthOfMovie += ", " + ageLimit.substring(3) + "+"
            }


            Text(

                text = movie.countries[0].country + ", " + lengthOfMovie, style = TextStyle(
                    fontSize = 12.sp, fontWeight = FontWeight(400), color = Color(0xFFB5B5C9)
                ), modifier = Modifier.padding(bottom = 3.dp)
            )

            Row(
                modifier = Modifier.padding(top = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(17.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(contentDescription = "",
                    tint = if (sharedViewModel.isMovieLiked(movie)) Color.Blue else Color.Unspecified,
                    painter = painterResource(R.drawable.heart),
                    modifier = Modifier.clickable {
                        sharedViewModel.toggleLikedStatus(movie)
                    })
                Icon(contentDescription = "",
                    tint = if (sharedViewModel.isMoviePrefer(movie)) Color.Blue else Color.Unspecified,
                    painter = painterResource(R.drawable.flag_icon),
                    modifier = Modifier.clickable {
                        sharedViewModel.togglePreferStatus(movie)
                    })
                Icon(
                    contentDescription = "Click if you already watched",
                    tint = if (sharedViewModel.isMovieWatched(movie)) Color.Blue else Color.Unspecified,
                    painter = painterResource(R.drawable.donot_show),
                    modifier = Modifier.clickable {
                        sharedViewModel.toggleWatchedStatus(movie)
                    }
                )
                Icon(contentDescription = "",
                    tint = Color.Unspecified,
                    painter = painterResource(R.drawable.share_icon),
                    modifier = Modifier.clickable { })
                Icon(contentDescription = "",
                    tint = Color.Unspecified,
                    painter = painterResource(R.drawable.more_icon),
                    modifier = Modifier.clickable { })
            }
        }
    }
}

//fun MoviesData.toMovie(): Movie {
//    return Movie(
//        kinopoiskId = this.kinopoiskId,
//        title = this.title ?:"",
//        image = this.image ?: "",
//        nameRu = this.nameRu ?: "",
//        nameEn = this.nameEn ?: "",
//        posterUrl = this.posterUrl ?: "",
//        posterUrlPreview = this.posterUrlPreview ?: "",
//        genres = this.genres ?: emptyList(),
//        countries = this.countries ?: emptyList(),
//        year = this.year ?: 0
//    )
//}
