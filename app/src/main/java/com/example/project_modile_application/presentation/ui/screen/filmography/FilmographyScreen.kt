package com.example.project_modile_application.presentation.ui.screen.filmography
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.project_modile_application.R
import com.example.project_modile_application.domain.dataclasses.Film
import com.example.project_modile_application.domain.dataclasses.StaffDataWithFilms
import com.example.project_modile_application.domain.viewModels.MovieDetailViewModel
import com.example.project_modile_application.domain.viewModels.SharedViewModel
import com.example.project_modile_application.presentation.ui.screen.filmpage.components.state.ActorsState
import com.example.project_modile_application.presentation.ui.screen.filmpage.components.state.GaleryState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

//@Preview(showBackground = true)
@Composable
fun FilmographyScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
    ) {
    val actorName = sharedViewModel.selectedActorName
    val films = sharedViewModel.selectedActorFilms
    Log.d("Films", films.value?.size.toString())
    Column(modifier = Modifier.fillMaxSize().padding(top=15.dp, start = 26.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon),
                    contentDescription = "Back icon",
                    modifier = Modifier.clickable(
                        onClick = {navController.popBackStack()
                        }
                    )
                )
                Spacer(modifier = Modifier.padding(horizontal = 65.dp))
                Text(text ="Фильмография", fontSize = 14.sp, fontWeight = FontWeight.W600)
            }



        Spacer(modifier = Modifier.padding(top = 40.dp))
        Text(text = actorName.value.toString(), fontSize = 18.sp, fontWeight = FontWeight.W600)
        Spacer(modifier = Modifier.padding(top = 10.dp))
    val chipItems = films.value?.map{ it.professionKey }?.distinct()
    val selectedChipIndex = remember { mutableStateOf(0) }


            LazyRow(
                modifier = Modifier.padding(0.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (chipItems != null) {
                    items(chipItems.size) { index ->
                        val name = chipItems[index]
                        val filteredFilms = films.value!!.filter {it.professionKey == name }
                        //val filteredFilms = allFilms.filter(filter) // Применяем фильтр к фильмам
                        if (name != null) {
                            ActorAndChip(
                                name = name,
                                count = filteredFilms.size, // Показываем количество фильмов
                                isSelected = selectedChipIndex.value == index,
                                onClick = { selectedChipIndex.value = index }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            val currentFilter = chipItems?.get(selectedChipIndex.value)
            val filteredFilms = films.value?.filter{it.professionKey == currentFilter}
            LazyColumn {
                if (filteredFilms != null) {
                    items(filteredFilms) { film ->
                        FilmCard(
                            filmName = film.nameRu,
                            aboutFilm = film.description,
                            posterUrl = film.posterUrl.toString()
                        )
                    }
                }
            }
    }
}

@Composable
fun ActorAndChip(
    name: String,
    count: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    AssistChip(
        onClick = onClick,
        label = { Text(text = "$name  $count") }, // Добавлено количество фильмов
        colors = AssistChipDefaults.assistChipColors(
            containerColor = if (isSelected) Color(0xFF3D3BFF) else Color.White,
            labelColor = if (isSelected) Color.White else Color.Black
        ),
        modifier = Modifier.padding(4.dp)
    )
}

@Composable
fun FilmCard(
    filmName: String,
    aboutFilm: String,
    posterUrl: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        AsyncImage(
            model = posterUrl,
            contentDescription = "Poster of $filmName",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .height(132.dp)
                .width(96.dp)
                .clip(RoundedCornerShape(size = 4.dp))
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = filmName, fontSize = 14.sp, fontWeight = FontWeight.W600)
            Text(text = aboutFilm, fontSize = 12.sp, fontWeight = FontWeight.W400)
        }
    }
}