package com.example.project_modile_application.ui.screen.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project_modile_application.R
import com.example.project_modile_application.ui.font.GraphikFontFamily

@Composable
fun MovieTab(hasGradient: Boolean = false, title: String = "title", genre: String = "genre") {
    Column {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(color = Color(0x77B5B5C9))
                .size(111.dp, 156.dp)
        ) {
            if (hasGradient) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                listOf(
                                    Color(0x003D3BFF),
                                    Color(0x773D3BFF)
                                )
                            )
                        )
                )
            }
            Column(
                modifier = Modifier
                    .padding(all = 6.dp)
                    .align(Alignment.TopEnd)
            ) {
                RatingTab()
            }
            if (hasGradient) {
                Image(
                    painter = painterResource(R.drawable.eye),
                    contentDescription = "",
                    modifier = Modifier.align(Alignment.BottomEnd)
                )
            }
        }

        Text(
            text = title,
            modifier = Modifier
                .padding(top = 8.dp),
            fontFamily = GraphikFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = Color(0xFF272727)
        )
        Text(
            text = genre,
            fontFamily = GraphikFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = Color(0xFF838390)
        )

    }
}