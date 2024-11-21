package com.example.project_modile_application.presentation.ui.screen.filmography
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project_modile_application.R

@Preview(showBackground = true)
@Composable
fun FilmographyScreen() {

    Column(modifier = Modifier.fillMaxSize().padding(top=50.dp, start = 20.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
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
                Spacer(modifier = Modifier.padding(horizontal = 50.dp))
                Text(text ="Фильмография", fontSize = 12.sp, fontWeight = FontWeight.W600)
            }


    Column() {
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Text(text = "ActorName", fontSize = 18.sp, fontWeight = FontWeight.W600)
        Spacer(modifier = Modifier.padding(top = 10.dp))
        val chipItems = listOf(
            ("Актриса"),
            ("Актриса дубляжа"),
            ("Актриса: играет саму себя")
        )

        LazyRow(
            modifier = Modifier.padding(0.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(chipItems) { name ->
                ActorAndChip(name)
            }
        }
    }


        



    }

}

@Composable
fun ActorAndChip(name: String) {
    AssistChip(
        onClick = { /* Действие при клике */ },
        label = { Text(text = name) },
        colors = AssistChipDefaults.assistChipColors(
            containerColor = Color.White,
            labelColor = Color.Black
        )
    )
}


@Composable
fun FilmCard(){

}


