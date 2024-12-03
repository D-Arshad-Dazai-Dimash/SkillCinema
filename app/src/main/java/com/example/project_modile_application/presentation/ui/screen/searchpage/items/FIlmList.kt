package com.example.project_modile_application.presentation.ui.screen.searchpage.items

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.project_modile_application.domain.dataclasses.FilmX
import com.example.project_modile_application.presentation.ui.screen.UIStateScreens.LoadingUIState

@Composable
fun FilmList(modifier: Modifier, films: List<FilmX>?) {
    Column(
        modifier = modifier
    ) {
        Log.d("films in list", films.toString())
        if (films != null) {
            if (films.isEmpty()) {
                LoadingUIState()
            } else {
                films.forEach { film ->
                    FilmRow(film = film)
                }
            }
        }
    }
}
