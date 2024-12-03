package com.example.project_modile_application.presentation.ui.screen.profilePage

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.project_modile_application.R
import com.example.project_modile_application.domain.viewModels.SharedViewModel
import com.example.project_modile_application.presentation.ui.font.GraphicFontFamily
import com.example.project_modile_application.presentation.ui.screen.filmpage.components.items.Header
import com.example.project_modile_application.presentation.ui.screen.profilePage.components.MoviesDataTab


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
    val likedMovies = sharedViewModel.likedMovie
    val preferMovies = sharedViewModel.preferMovie
    val collections = sharedViewModel.collections

    Log.d("F", watchedMovies.toString())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
//                .verticalScroll(rememberScrollState(0))
                .background(Color.White)
                .padding(start = 26.dp)
        ) {
            Header(
                "Просмотрено",
                watchedMovies,
                onClick = {},
                Modifier.padding(top = 96.dp, end = 26.dp)
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(bottom = 36.dp)
            ) {
                items(watchedMovies) { movie ->
                    MoviesDataTab(movie, navController, sharedViewModel)
                }
            }
            Text(
                text = "Коллекции",
                fontWeight = W600,
                fontFamily = GraphicFontFamily,
                fontSize = 18.sp,
                lineHeight = 19.8.sp
            )

            NewCollectionButton {
                val newCollectionName = "Collection ${sharedViewModel.collections.size + 1}"
                sharedViewModel.addCollection(newCollectionName)
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize().padding(end = 26.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    CollectionItem(
                        sharedViewModel,
                        collections,
                        collectionName = "Любимые",
                        painter = painterResource(R.drawable.heart),
                        false
                    ) {
                    }
                }

                item {
                    CollectionItem(
                        sharedViewModel,
                        collections,
                        collectionName = "Хочу посмотреть",
                        painter = painterResource(R.drawable.flag_icon),
                        false
                    ) {
                    }
                }

                items(collections.size) { index ->
                    if (collections[index] != "Любимые" && collections[index] != "Хочу посмотреть") {
                        CollectionItem(
                            sharedViewModel,
                            collections,
                            collectionName = collections[index],
                            painter = painterResource(R.drawable.iconperson),
                            true
                        ) {
                            sharedViewModel.removeCollection(collections[index])
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun NewCollectionButton(onCreate: () -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { onCreate() }
        .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(R.drawable.plus_icon),
            contentDescription = "Add Collection",
            modifier = Modifier.size(24.dp),
            tint = Color.DarkGray
        )
        Text(
            text = "Создать свою коллекцию",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            fontFamily = GraphicFontFamily,
            fontWeight = W400,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}


@Composable
fun CollectionItem(
    sharedViewModel: SharedViewModel,
    collection : List<String>,
    collectionName: String,
    painter: Painter,
    ableToRemove: Boolean,
    onRemove: () -> Unit,
) {
    Column(
        modifier = Modifier
            .size(height = 146.dp , width = 146.dp)
            .background(Color.White)
            .border(1.dp, color = Color.Black, RoundedCornerShape(16.dp))
    ) {
        if (ableToRemove) {
            IconButton(
                onClick = onRemove,
                modifier = Modifier.padding(start = 117.dp, top = 3.dp),
            ) {
                Icon(
                    painter = painterResource(R.drawable.close_icon),
                    contentDescription = "Remove Collection",
                )
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painter,
                    contentDescription = ""
                )
                Text(
                    text = collectionName,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(8.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painter,
                    contentDescription = ""
                )
                Text(
                    text = collectionName,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}



