package com.example.project_modile_application.presentation.ui.screen.filmpage.components.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.project_modile_application.domain.dataclasses.SimilarMovie

@Composable
fun SimilarMoviesShow(similarMovies: List<SimilarMovie> , navController: NavController) {
    if (similarMovies.isNotEmpty()) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Header("Похожие фильмы", similarMovies,{})
            }
            Spacer(Modifier.height(32.dp))
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)

            ) {
                items(similarMovies) { similarFilm ->
                    SimilarCard(similarFilm, onClick = {navController.navigate("detailMovie/${similarFilm.filmId}")})
                }
            }
        }
        Spacer(Modifier.height(32.dp))
    }
}

@Composable
fun SimilarCard(similarFilm: SimilarMovie, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .width(111.dp)
            .height(194.dp)
            .clickable {
                onClick()
            }
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .fillMaxWidth()
                .height(156.dp)
        ) {
            AsyncImage(
                model = similarFilm.posterUrl,
                contentScale = ContentScale.Crop,
                contentDescription = ""
            )
        }
        Spacer(Modifier.height(8.dp))
        Text(
            text = similarFilm.nameOriginal.toString(),
            fontSize = 14.sp,
            color = Color(0xFF272727)
        )
        Spacer(Modifier.height(2.dp))
    }
}