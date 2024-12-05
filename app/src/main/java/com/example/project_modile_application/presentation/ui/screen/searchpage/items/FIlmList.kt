package com.example.project_modile_application.presentation.ui.screen.searchpage.items

import android.util.Log
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project_modile_application.domain.dataclasses.FilmX
import com.example.project_modile_application.presentation.ui.screen.UIStateScreens.LoadingUIState

@Composable
fun FilmList(films: List<FilmX>?) {
    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
        ) {
            Log.d("films in list", films.toString())
            if (films.isNullOrEmpty()) {
                Text(
                    text = "По вашему запросу\nничего не найдено",
                    fontSize = 20.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 30.dp)
                )
            } else {
                films.forEach { film ->
                    FilmRow(film = film)
                }
            }
        }
    }
}
