package com.example.project_modile_application.presentation.ui.screen.profilePage.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.project_modile_application.R
import com.example.project_modile_application.data.local.entities.CollectionEntity


@Composable
fun CollectionItem(
    collection: CollectionEntity,
    painter: Painter,
    ableToDelete: Boolean,
    onRemove: () -> Unit
) {
    Column(
        modifier = Modifier
            .size(height = 146.dp, width = 146.dp)
            .background(Color.White)
            .border(1.dp, color = Color.Black, RoundedCornerShape(16.dp))
    ) {
        if (ableToDelete) {
            IconButton(
                onClick = onRemove,
                modifier = Modifier.padding(start = 117.dp, top = 3.dp),
            ) {
                Icon(
                    painter = painterResource(R.drawable.close_icon),
                    contentDescription = "Remove Collection",
                )
            }
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painter,
                contentDescription = ""
            )
            Text(
                text = collection.name,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
