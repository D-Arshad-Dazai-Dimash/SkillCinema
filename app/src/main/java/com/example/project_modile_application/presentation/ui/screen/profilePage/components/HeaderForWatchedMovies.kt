package com.example.project_modile_application.presentation.ui.screen.profilePage.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project_modile_application.R
import com.example.project_modile_application.data.local.entities.MovieEntity

@Composable
fun HeaderForWatchedMovies(
    topic: String,                // Title of the section (e.g., "Watched Movies")
    watchedMovies: MutableState<List<MovieEntity>>, // List of watched movies from Room database
    onViewAllClick: () -> Unit,   // Action to perform when "View All" is clicked
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = topic,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF272727),
            )
        )
        Row(
            modifier = Modifier.clickable {
                onViewAllClick()
            },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${watchedMovies.value.size} Movies",
                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF3D3BFF),
                )
            )
            Image(
                painter = painterResource(R.drawable.right_arrow_icon),
                contentDescription = "View All Watched Movies",
                modifier = Modifier.size(18.dp),
                colorFilter = ColorFilter.tint(Color(0xFF3D3BFF))
            )
        }
    }
}
