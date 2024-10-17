package com.example.project_modile_application.ui.screen.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project_modile_application.ui.font.GraphikFontFamily

@Composable
fun RatingTab(rating: Double = 7.8) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .size(17.dp, 10.dp)
            .background(color = Color(0xFF3D3BFF))
    )
    {
        Text(
            text = rating.toString(),
            modifier = Modifier
                .align(Alignment.Center),
            fontFamily = GraphikFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 6.sp,
            color = Color.White
        )
    }
}