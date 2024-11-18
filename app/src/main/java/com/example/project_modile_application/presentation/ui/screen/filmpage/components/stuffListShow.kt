package com.example.project_modile_application.presentation.ui.screen.filmpage.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.project_modile_application.R
import com.example.project_modile_application.domain.dataclasses.StaffData

@Composable
fun StuffListShow(
    actors: List<StaffData>,
    topic: String,
    chunkSize: Int,
    navController: NavController
) {
    if (actors.isNotEmpty()) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                descItem(topic, actors, {})
            }
            Spacer(Modifier.height(32.dp))
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)

            ) {

                items(actors.chunked(chunkSize)) { sortedActor ->
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        sortedActor.forEach { actor ->
                            ActorCard(
                                actor = actor,
                                onClick = { navController.navigate("staffDetails/${actor.staffId}") }
                            )
                        }
                    }
                }
            }
            Spacer(Modifier.height(32.dp))
        }
    }
}


@Composable
fun ActorCard(
    actor: StaffData,
    onClick: () -> Unit
) {
    Row(
        Modifier
            .fillMaxSize()
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = actor.posterUrl,
            contentDescription = "",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .height(70.dp)
                .width(50.dp)
                .clip(RoundedCornerShape(size = 4.dp))

        )
        Column(
            modifier = Modifier.padding(horizontal = 18.dp)
        ) {
            Text(
                text = actor.nameRu,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF272727),
                )
            )
            Text(
                text = actor.professionText,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF838390),
                )
            )
        }
    }
}


@Composable
fun descItem(topic: String, list: List<Any>, onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = topic,
            style = TextStyle(
                fontSize = 20.sp,

                fontWeight = FontWeight(600),
                color = Color(0xFF272727),
            )
        )
        Row(
            modifier = Modifier.clickable {
                onClick()
            },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = list.size.toString(), style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF3D3BFF),
                )
            )
            Image(
                painter = painterResource(R.drawable.right_arrow_icon),
                contentDescription = "",
                modifier = Modifier
                    .size(18.dp),
                colorFilter = ColorFilter.tint(Color(0xFF3D3BFF))
            )
        }
    }
}