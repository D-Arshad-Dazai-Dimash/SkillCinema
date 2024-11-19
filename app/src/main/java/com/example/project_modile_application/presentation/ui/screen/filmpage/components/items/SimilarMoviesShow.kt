package com.example.project_modile_application.presentation.ui.screen.filmpage.components.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.project_modile_application.domain.dataclasses.SimilarMovie

@Composable
fun SimilarMoviesShow(similarMovies: List<SimilarMovie>) {
    if (similarMovies.isNotEmpty()) {
        Column(
            Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Header("Похожие фильмы", similarMovies, {})
            LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                items(similarMovies) { movie ->
                    AsyncImage(
                        model = movie.posterUrl,
                        contentDescription = "",
                        modifier = Modifier
                            .height(400.dp)
                            .width(200.dp)
                    )
                }
            }
        }
    }
}