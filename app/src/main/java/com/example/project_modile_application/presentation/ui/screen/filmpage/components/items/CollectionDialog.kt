package com.example.project_modile_application.presentation.ui.screen.filmpage.components.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.project_modile_application.data.local.entities.CollectionEntity
import com.example.project_modile_application.data.local.entities.MovieEntity
import com.example.project_modile_application.domain.viewModels.RoomViewModel

@Composable
fun CollectionDialog(
    roomViewModel: RoomViewModel,
    movie: MovieEntity,
    onDismiss: () -> Unit
) {
    val collections = roomViewModel.collections
    val movieInCollections = remember { mutableStateOf(emptyList<CollectionEntity>()) }
    val newCollectionName = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        roomViewModel.getMovieCollections(movie.kinopoiskId) {
            movieInCollections.value = it
        }
    }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = "Добавить в коллекцию") },
        text = {
            Column {
                collections.value.forEach { collection ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable {
                            if (movieInCollections.value.contains(collection)) {
                                roomViewModel.removeMovieFromCollection(movie, collection.id)
                                movieInCollections.value = movieInCollections.value - collection
                            } else {
                                roomViewModel.addMovieToCollection(movie, collection.id)
                                movieInCollections.value = movieInCollections.value + collection
                            }
                        }
                    ) {
                        Checkbox(
                            checked = movieInCollections.value.contains(collection),
                            onCheckedChange = { isChecked ->
                                if (isChecked) {
                                    roomViewModel.addMovieToCollection(movie, collection.id)
                                    movieInCollections.value = movieInCollections.value + collection
                                } else {
                                    roomViewModel.removeMovieFromCollection(movie, collection.id)
                                    movieInCollections.value = movieInCollections.value - collection
                                }
                            }
                        )
                        Text(text = collection.name, modifier = Modifier.padding(start = 8.dp))
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = newCollectionName.value,
                    onValueChange = { newCollectionName.value = it },
                    label = { Text(text = "Новая коллекция") },
                    modifier = Modifier.fillMaxWidth()
                )
                Button(
                    onClick = {
                        if (newCollectionName.value.isNotBlank()) {
                            roomViewModel.addCollection(newCollectionName.value)
                            newCollectionName.value = ""
                        }
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Создать")
                }
            }
        },
        confirmButton = {
            Button(onClick = { onDismiss() }) {
                Text("Готово")
            }
        }
    )
}
