package com.example.project_modile_application.ui.screen.listingPage

import MovieTab
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.project_modile_application.data.PosterData


@Composable
fun IntoCategory_Grid(movies: List<PosterData>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {}
            items(movies.size) { index ->
                MovieTab(movies[index])
            }
    }
}
