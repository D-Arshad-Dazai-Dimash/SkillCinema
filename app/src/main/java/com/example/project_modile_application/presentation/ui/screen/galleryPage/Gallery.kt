package com.example.project_modile_application.presentation.ui.screen.galleryPage

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.project_modile_application.domain.dataclasses.Images
import com.example.project_modile_application.domain.viewModels.GalleryViewModel
import com.example.project_modile_application.presentation.ui.screen.galleryPage.components.NavBar
import com.example.project_modile_application.presentation.ui.screen.galleryPage.components.state.GalleryIntent
import com.example.project_modile_application.presentation.ui.screen.galleryPage.components.state.GalleryState


@Preview(showBackground = true)
@Composable
fun GalleryPrev() {
    GalleryPage(navController = rememberNavController(), 0)
}

@Composable
fun GalleryPage(
    navController: NavController,
    movieId: Int,
    viewModel: GalleryViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()
    Log.d("B", state.toString())
    LaunchedEffect(movieId) {
        viewModel.sendIntent(GalleryIntent.LoadGalleryIntent(movieId))
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        NavBar(navController, "Галерея")
        when (state) {
            is GalleryState.Loading -> CircularProgressIndicator()
            is GalleryState.Success -> {
                Log.d("C", "Loke")
                GalleryLayout(images = (state as GalleryState.Success).images)
            }

            is GalleryState.Error -> Text("error")
        }
    }
}

@Composable
fun GalleryLayout(modifier: Modifier = Modifier, images: Images?) {
    Log.d("INSIDE", "LAyout")
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 84.dp, start = 26.dp, end = 26.dp)
    ) {
        if (images != null) {
            items(images.items.size) { index ->
                when {
                    index % 3 == 2 -> {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(173.dp)
                                .padding(8.dp)
                                .clip(RoundedCornerShape(5)),
                            contentAlignment = Alignment.Center
                        ) {
                            AsyncImage(
                                model = images.items[index].imageUrl,
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }

                    index % 3 == 1 -> {}
                    else -> {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(120.dp)
                                    .padding(8.dp)
                                    .clip(RoundedCornerShape(5)),
                                contentAlignment = Alignment.Center
                            ) {
                                AsyncImage(
                                    model = images.items[index].imageUrl,
                                    contentDescription = "",
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                            }
                            if (index + 1 < images.items.size) {
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(120.dp)
                                        .padding(8.dp)
                                        .clip(RoundedCornerShape(5)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    AsyncImage(
                                        model = images.items[index + 1].imageUrl,
                                        contentDescription = "",
                                        modifier = Modifier.fillMaxSize(),
                                        contentScale = ContentScale.Crop
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}