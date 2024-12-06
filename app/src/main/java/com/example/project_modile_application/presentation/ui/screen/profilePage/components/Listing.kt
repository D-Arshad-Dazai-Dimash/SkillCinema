package com.example.project_modile_application.presentation.ui.screen.profilePage.components

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.project_modile_application.R
import com.example.project_modile_application.data.local.entities.CollectionEntity
import com.example.project_modile_application.domain.viewModels.RoomViewModel
import com.example.project_modile_application.presentation.navigation.Screen

@Composable
fun Listing(
    navController: NavController,
) {

    val parentEntry = try {
        navController.getBackStackEntry(Screen.Profile.route)
    } catch (e: IllegalArgumentException){
        null
    }

    val roomViewModel: RoomViewModel = if (parentEntry !=null) {
        viewModel(parentEntry)
    } else {
        viewModel()
    }

    val collection by roomViewModel.selectedCollection.collectAsState()

    val movies =
        remember { mutableStateOf(listOf<com.example.project_modile_application.data.local.entities.MovieEntity>()) }

    LaunchedEffect(collection.id) {
        roomViewModel.getMoviesInCollection(collection.id) { fetchedMovies ->
            movies.value = fetchedMovies
        }
    }

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
                    modifier = Modifier.size(24.dp)
                )
            }
            Text(
                text = collection.name,
                fontFamily = com.example.project_modile_application.presentation.ui.font.GraphicFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                lineHeight = 20.sp,
                color = Color(0xff272727),
                modifier = Modifier.padding(start = 100.dp)
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(movies.value.size) { index ->
                val movie = movies.value[index]
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    MoviesDataTab(movie = movie, navController = navController)
                }
            }
        }
    }
}
