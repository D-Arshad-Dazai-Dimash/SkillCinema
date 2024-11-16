package com.example.project_modile_application.presentation.ui.screen.listingPage

import MovieTab
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.project_modile_application.R
import com.example.project_modile_application.data.MoviesData
import com.example.project_modile_application.domain.SharedViewModel

@Composable
fun IntoCategory_Grid(navController: NavController, movies: List<MoviesData> , sharedViewModel: SharedViewModel) {
    Column(
        verticalArrangement = Arrangement.spacedBy(26.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 26.dp, top = 16.dp)
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    painter = painterResource(R.drawable.icon_arrow_back),
                    contentDescription = "Back",
                    modifier = Modifier.size(5.5.dp, 9.5.dp)
                )
            }
            Text(
                text = sharedViewModel.category.value.category,
                fontFamily = com.example.project_modile_application.presentation.ui.font.GraphicFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                lineHeight = 13.2.sp,
                color = Color(0xff272727),
                modifier = Modifier.padding(start = 100.dp)
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 60.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(movies.size) { index ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 2.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    MovieTab(movies[index] , navController , sharedViewModel)
                }
            }
        }
    }
}
