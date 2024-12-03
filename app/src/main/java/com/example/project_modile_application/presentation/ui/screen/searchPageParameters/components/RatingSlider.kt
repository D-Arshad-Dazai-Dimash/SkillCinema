package com.example.project_modile_application.presentation.ui.screen.searchPageParameters.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RatingSlider(onRatingChange: (ClosedFloatingPointRange<Float>) -> Unit = {_ ->}) {
    var ratingRange by remember { mutableStateOf(1f..10f) }
    Column (
        modifier = Modifier
            .padding(horizontal = 26.dp, vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Рейтинг")
            Text(text = if (ratingRange == 1f..10f) {
                "Любой"
            } else {
                "От ${"%.1f".format(ratingRange.start)} До ${"%.1f".format(ratingRange.endInclusive)}"
            })
        }
        Spacer(modifier = Modifier.height(16.dp))
        RangeSlider(
            value = ratingRange,
            onValueChange = { ratingRange = it },
            valueRange = 1f..10f,
            colors = SliderDefaults.colors(
                thumbColor = Color.White,
                activeTrackColor = Color.Blue,
                inactiveTrackColor = Color.Gray,
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "1")
            Text(text = "10")
        }
    }
}