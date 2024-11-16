package com.example.project_modile_application.presentation.ui.screen.filmpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.project_modile_application.R
import com.example.project_modile_application.domain.SharedViewModel

@Composable
fun FilmPageScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    val selectedMovie = sharedViewModel.selectedMovie.value

    if (selectedMovie == null) {
        // loading/error jasau kerek
        Text("No movie selected!")
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(400.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 42.dp, bottom = 142.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.icon_arrow_back),
                            contentDescription = "",
                            modifier = Modifier
                                .size(24.dp)
                                .padding(top = 16.dp, start = 16.dp, bottom = 16.dp),
                        )
                    }
                    Text(
                        text = selectedMovie.nameRu,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    AsyncImage(
                        model = selectedMovie.posterUrl,
                        contentDescription = selectedMovie.nameRu,
                        modifier = Modifier
                            .height(400.dp)
                            .fillMaxWidth()
                    )
                    Text(
                        text = selectedMovie.description,
                        modifier = Modifier.padding(top = 16.dp),
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}