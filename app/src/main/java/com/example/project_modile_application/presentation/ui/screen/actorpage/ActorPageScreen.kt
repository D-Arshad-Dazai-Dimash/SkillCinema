package com.example.project_modile_application.presentation.ui.screen.actorpage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.project_modile_application.R
import com.example.project_modile_application.domain.dataclasses.Film
import com.example.project_modile_application.domain.viewModels.ActorDetailViewModel

//@Preview(showBackground = true)
@Composable
fun ActorPageScreen(
    navController: NavController,
    staffDetailViewModel: ActorDetailViewModel = viewModel()
) {
    val staffState by staffDetailViewModel.stateStaff.collectAsState()
    if (staffState.isLoading) {
        CircularProgressIndicator()
    } else if (staffState.error.isNotBlank()) {
        Text(
            text = staffState.error,
        )
    }
    else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp)
        ) {

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)) {
                IconButton(
                    onClick = {
                    },
                    modifier = Modifier
                        .background(Color.Transparent)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_arrow_back),
                        contentDescription = "Back icon",
                    )
                }
            }
            val staffInfo = staffState.staff
            if (staffInfo != null) {
                ActorInformation(staffInfo.nameRu, staffInfo.posterUrl, staffInfo.profession)
                BestFilms(staffInfo.films)
                Filmography(staffInfo.films)
            }
        }
    }

}

@Composable
fun ActorInformation(name: String, imageUrl: Any?=R.drawable.ic_launcher_foreground, profession: String){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 26.dp)
    ){
        AsyncImage(
            model = imageUrl,
            contentDescription = "",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .height(201.dp)
                .width(146.dp)
                .clip(RoundedCornerShape(size = 4.dp))
        )
        Column {
            Text(text = name, fontSize = 16.sp, fontWeight = FontWeight(600))
            Text(text = profession, fontSize = 12.sp, fontWeight = FontWeight(400))
        }
    }
}

@Composable
fun BestFilms(films: List<Film>) {

    Column (modifier = Modifier.padding(35.dp)){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(text = "Лучшее", fontSize = 18.sp, fontWeight = FontWeight(600))
            Icon(painter = painterResource(R.drawable.frame_7299), contentDescription = "")
        }
        LazyRow() {
            items(8){
                val film = films[it]
                FilmCard(film, film.professionKey)
            }
        }


    }
}

@Composable
fun FilmCard(movie: Film, professionKey: String) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        AsyncImage(
            model = movie.posterUrl,
            contentDescription = movie.nameRu,
            modifier = Modifier
                .height(180.dp)
                .width(111.dp)
                .clip(RoundedCornerShape(4.dp))
        )
        Text(
            text = movie.nameRu,
            modifier = Modifier
                .padding(top = 8.dp)
                .width(111.dp)
                .clickable {

                    // navController.navigate("movieData/${movie.kinopoiskId}")
                },
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontFamily = com.example.project_modile_application.presentation.ui.font.GraphicFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = Color(0xFF272727)
        )
        Text(
            text = professionKey,
            fontFamily = com.example.project_modile_application.presentation.ui.font.GraphicFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = Color(0xFF838390)
        )

    }
}



@Composable
fun Filmography(films: List<Film>) {
    Column(modifier = Modifier.padding(35.dp)) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(text = "Фильмография", fontSize = 18.sp, fontWeight = FontWeight(600))
        Icon(painter = painterResource(R.drawable.kspisku), contentDescription = "kspisku")
    }
    Text(text = films.size.toString(), fontSize = 12.sp, fontWeight = FontWeight(400))
    }
}