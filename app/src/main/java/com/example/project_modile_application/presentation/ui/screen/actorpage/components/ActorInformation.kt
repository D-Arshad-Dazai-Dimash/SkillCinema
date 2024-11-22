package com.example.project_modile_application.presentation.ui.screen.actorpage.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.project_modile_application.R


@Composable
fun ActorInformation(
    name: String,
    imageUrl: Any? = R.drawable.ic_launcher_foreground,
    profession: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth().padding(vertical = 15.dp)
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = "",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .height(201.dp)
                .width(146.dp)
                .clip(RoundedCornerShape(size = 4.dp))
        )
        Spacer(modifier = Modifier.padding(horizontal = 10.dp))
        Column {
            Text(text = name, fontSize = 20.sp, fontWeight = FontWeight(600))
            Text(text = profession, fontSize = 12.sp, fontWeight = FontWeight(400))
        }
    }
}
