package com.example.project_modile_application.presentation.ui.screen.profilePage

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
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
import com.example.project_modile_application.data.local.entities.CollectionEntity
import com.example.project_modile_application.domain.viewModels.SharedViewModel
import com.example.project_modile_application.presentation.ui.font.GraphicFontFamily
import com.example.project_modile_application.presentation.ui.screen.profilePage.components.HeaderForWatchedMovies
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


@Composable
fun NewCollectionButton(sharedViewModel: SharedViewModel) {
    val showDialog = remember { mutableStateOf(false) }
    val newCollectionName = remember { mutableStateOf("") }

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = {
                Text(
                    text = "Новая коллекция",
                    fontFamily = GraphicFontFamily,
                    fontWeight = W600,
                    fontSize = 18.sp
                )
            },
            text = {
                Column {
                    Text(text = "Введите название коллекции:")
                    OutlinedTextField(
                        value = newCollectionName.value,
                        onValueChange = { newCollectionName.value = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (newCollectionName.value.isNotBlank()) {
                            sharedViewModel.addCollection(newCollectionName.value)
                        }
                        newCollectionName.value = ""
                        showDialog.value = false
                    }
                ) {
                    Text("Создать")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        newCollectionName.value = ""
                        showDialog.value = false
                    }
                ) {
                    Text("Отмена")
                }
            }
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { showDialog.value = true }
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
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
    collection: CollectionEntity,
    painter: Painter,
    ableToDelete: Boolean,
    onRemove: () -> Unit
) {
    Column(
        modifier = Modifier
            .size(height = 146.dp, width = 146.dp)
            .background(Color.White)
            .border(1.dp, color = Color.Black, RoundedCornerShape(16.dp))
    ) {
        if (ableToDelete) {
            IconButton(
                onClick = onRemove,
                modifier = Modifier.padding(start = 117.dp, top = 3.dp),
            ) {
                Icon(
                    painter = painterResource(R.drawable.close_icon),
                    contentDescription = "Remove Collection",
                )
            }
        }
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
                text = collection.name,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
