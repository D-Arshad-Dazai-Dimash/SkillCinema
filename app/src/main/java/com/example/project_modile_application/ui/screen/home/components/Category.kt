package com.example.project_modile_application.ui.screen.home.components

import MovieTab
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.project_modile_application.data.PosterData
import com.example.project_modile_application.model.Movie
import com.example.project_modile_application.navigation.Screen
import com.example.project_modile_application.ui.font.GraphicFontFamily

@Composable
fun Category(categoryName: String, movies: List<PosterData>, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 36.dp,
                bottom = 24.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = categoryName,
            fontFamily = GraphicFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            color = Color(0xff272727)
        )
        Text(
            text = "Все",
            modifier = Modifier.clickable(onClick = {
//                navController.navigate(Screen.ListingPage.route + "/" + categoryName)
                navController.navigate(Screen.ListingPage.route + "/" + "premiers")
            }),
            fontFamily = GraphicFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            color = Color(0xFF3D3BFF)
        )
    }
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(movies) {
            MovieTab(it)
        }
        item {
            ShowAll()
        }

    }
}