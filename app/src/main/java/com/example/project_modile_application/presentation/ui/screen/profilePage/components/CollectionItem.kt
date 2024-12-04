package com.example.project_modile_application.presentation.ui.screen.profilePage.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project_modile_application.R
import com.example.project_modile_application.data.local.entities.CollectionEntity
import com.example.project_modile_application.presentation.ui.font.GraphicFontFamily


@Composable
fun CollectionItem(
    collection: CollectionEntity,
    painter: Painter,
    ableToDelete: Boolean,
    movieCount: Int,
    onRemove: () -> Unit
) {

    Box() {
        Column(
            modifier = Modifier
                .size(height = 165.dp, width = 165.dp)
                .background(Color.White)
                .border(1.dp, color = Color.Black, RoundedCornerShape(16.dp)),
            horizontalAlignment = Alignment.CenterHorizontally
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
            }else{
                Spacer(modifier = Modifier.padding(25.dp))
            }
                if(collection.name == "Хочу посмотреть"){
                    Icon(
                        painter = painterResource(R.drawable.flag_icon),
                        contentDescription = "",
                        modifier = Modifier.size(20.dp)
                    )
                }else if (collection.name == "Нравится"){
                    Icon(
                        painter = painterResource(R.drawable.heart),
                        contentDescription = "",
                        modifier = Modifier.size(20.dp)
                    )
                }
                else {
                    Icon(
                        painter = painter,
                        contentDescription = "",
                        modifier = Modifier.size(20.dp)
                    )
                }
                Text(
                    text = collection.name,
                    textAlign = TextAlign.Center,
                    fontFamily = GraphicFontFamily,
                    fontWeight = W400,
                    fontSize = 12.sp,
                    lineHeight = 13.2.sp,
                    modifier = Modifier.padding(8.dp)
                )

                Box(
                    modifier = Modifier
                        .background(Color(0xFF3D3BFF) , RoundedCornerShape(16.dp))
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                        .size(25.dp , 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "$movieCount",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White,
                        fontWeight = W500,
                        fontSize = 8.sp,
                        lineHeight = 8.8.sp,
                        textAlign = TextAlign.Center

                    )
                }
            }
        }
}
