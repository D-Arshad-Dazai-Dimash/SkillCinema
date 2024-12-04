package com.example.project_modile_application.presentation.ui.screen.profilePage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.project_modile_application.R
import com.example.project_modile_application.domain.viewModels.SharedViewModel
import com.example.project_modile_application.presentation.ui.font.GraphicFontFamily
import com.example.project_modile_application.presentation.ui.screen.profilePage.components.CollectionItem
import com.example.project_modile_application.presentation.ui.screen.profilePage.components.HeaderForWatchedMovies
import com.example.project_modile_application.presentation.ui.screen.profilePage.components.MoviesDataTab
import com.example.project_modile_application.presentation.ui.screen.profilePage.components.NewCollectionButton


@Preview
@Composable
fun PProfileScree() {
    val navController = rememberNavController()
    val sharedViewModel = remember { SharedViewModel() }
    ProfileScreen(navController, sharedViewModel)
}

@Composable
fun ProfileScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    val watchedMovies = sharedViewModel.watchedMovies
    val collections = sharedViewModel.collections

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(start = 26.dp)
        ) {
            HeaderForWatchedMovies(
                topic = "Watched Movies",
                watchedMovies = watchedMovies,
                onViewAllClick = {},
                modifier = Modifier.padding(top = 96.dp, end = 26.dp)
            )

            if (watchedMovies.value.isNotEmpty()) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(bottom = 36.dp)
                ) {
                    items(watchedMovies.value.size) { movie ->
                        MoviesDataTab(
                            movie = watchedMovies.value[movie],
                            navController = navController,
                            sharedViewModel = sharedViewModel
                        )
                    }
                }
            } else {
                Text(
                    text = "No movies watched yet.",
                    fontWeight = W400,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 36.dp)
                )
            }

            Text(
                text = "Коллекции",
                fontWeight = W600,
                fontFamily = GraphicFontFamily,
                fontSize = 18.sp,
                lineHeight = 19.8.sp
            )

            NewCollectionButton(sharedViewModel)

            if (collections.value.isNotEmpty()) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(end = 26.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {


                    items(collections.value.size) { collection ->
                        CollectionItem(
                            collection = collections.value[collection],
                            painter = painterResource(R.drawable.iconperson),
                            ableToDelete = true,
                            onRemove = { sharedViewModel.removeCollection(collections.value[collection].id) }
                        )
                    }
                }
            } else {
                Text(
                    text = "No collections yet.",
                    fontWeight = W400,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }
}
