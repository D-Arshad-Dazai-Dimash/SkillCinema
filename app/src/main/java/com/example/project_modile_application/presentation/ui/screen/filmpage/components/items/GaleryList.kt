package com.example.project_modile_application.presentation.ui.screen.filmpage.components.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.project_modile_application.domain.dataclasses.Image
import com.example.project_modile_application.presentation.navigation.Screen

@Composable
fun GalleryListItem(images: List<Image>, navController: NavController) {
    if (images.isNotEmpty()) {
        Column(
            Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Header("Галерея", images, {navController.navigate(Screen.GalleryPage.route)})
            LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                items(images) { image ->
                    AsyncImage(
                        model = image.imageUrl,
                        contentDescription = "",
                        modifier = Modifier
                            .height(400.dp)
                            .width(200.dp)
                    )
                }
            }
        }
    }
}