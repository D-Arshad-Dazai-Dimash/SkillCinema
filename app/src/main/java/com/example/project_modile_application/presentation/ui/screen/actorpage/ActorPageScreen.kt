package com.example.project_modile_application.presentation.ui.screen.actorpage

import MovieTab
import android.service.carrier.MessagePdu
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.project_modile_application.R
import com.example.project_modile_application.domain.dataclasses.StaffData
import com.example.project_modile_application.presentation.ui.screen.filmpage.components.items.ActorCard

@Preview(showBackground = true)
@Composable
fun ActorPageScreen(
) {

    Column(
        modifier = Modifier.fillMaxSize().padding(top = 50.dp)
    ) {

        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)){
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
        ActorInformation()
        BestFilms()
        Filmography()
    }

}

@Composable
fun ActorInformation(){
    Row (
        modifier = Modifier.fillMaxWidth().padding(horizontal = 26.dp)
    ){
        AsyncImage(
            model = R.drawable.ic_launcher_foreground,
            contentDescription = "",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .height(201.dp)
                .width(146.dp)
                .clip(RoundedCornerShape(size = 4.dp))
        )
        Column {
            Text(text = "ActorName", fontSize = 16.sp, fontWeight = FontWeight(600))
            Text(text = "Actor", fontSize = 12.sp, fontWeight = FontWeight(400))
        }
    }
}

@Composable
fun BestFilms(){
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
                FilmCard()
            }
        }


    }
}

@Composable
fun FilmCard(){
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        AsyncImage(
            model = "movie.image.takeIf { it.isNotEmpty() } ?: ",
            contentDescription = "movie.title",
            modifier = Modifier
                .height(180.dp)
                .width(111.dp)
                .clip(RoundedCornerShape(4.dp))
        )
        Text(
            text = "movie.title",
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
            text = ("movie"),
            fontFamily = com.example.project_modile_application.presentation.ui.font.GraphicFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = Color(0xFF838390)
        )

    }
}



@Composable
fun Filmography(){
    Column(modifier = Modifier.padding(35.dp)) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(text = "Фильмография", fontSize = 18.sp, fontWeight = FontWeight(600))
        Icon(painter = painterResource(R.drawable.kspisku), contentDescription = "kspisku")
    }
    Text(text = "num films", fontSize = 12.sp, fontWeight = FontWeight(400))
    }
}