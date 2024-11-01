package com.example.project_modile_application.ui.screen.listingPage

import MovieItem
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.project_modile_application.data.PosterData
import com.example.project_modile_application.model.Movie


@Composable
fun IntoCategory_Grid(movies: List<PosterData>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {}
            items(movies.size) { index ->
                MovieItem(movies[index])
            }
    }
}
