package com.example.project_modile_application.ui.screen.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project_modile_application.R
import com.example.project_modile_application.ui.font.GraphikFontFamily

@Composable
fun ShowAll() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .size(111.dp, 156.dp)
    ) {
        Button(
            modifier = Modifier
                .padding(top = 51.5.dp)
                .clip(CircleShape)
                .size(48.dp),
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFFFFF),
            ),
        ) {
            Image(
                painter = painterResource(R.drawable.right_arrow),
                contentDescription = "",
                colorFilter = ColorFilter.tint(Color.Black)
            )
        }
        Text(
            text = "Показать все",
            modifier = Modifier
                .padding(8.dp),
            fontFamily = GraphikFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        )
    }
}