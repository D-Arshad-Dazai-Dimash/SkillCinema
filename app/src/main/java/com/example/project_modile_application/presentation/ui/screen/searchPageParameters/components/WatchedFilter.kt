package com.example.project_modile_application.presentation.ui.screen.searchPageParameters.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.project_modile_application.R

@Composable
fun WatchedFilter(watched: Boolean = false, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(vertical = 16.dp, horizontal = 26.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.crossed_eye),
            contentDescription = "",
            colorFilter = if (watched) {
                ColorFilter.tint(Color.Blue) // Tint with gray if watched
            } else {
                ColorFilter.tint(Color.Gray) // Tint with black if not watched
            }
        )
        Text(
            text = "Не просмотрен",
            modifier = Modifier.padding(start = 24.dp)
        )
    }
}