package com.example.project_modile_application.presentation.ui.screen.galleryPage.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.project_modile_application.R
import com.example.project_modile_application.presentation.ui.font.GraphicFontFamily

@Composable
fun NavBar(navController: NavController?, title: String = "") {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(top = 42.dp, start = 26.dp, end = 26.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_arrow_back),
            contentDescription = "",
            modifier = Modifier.clickable {
                navController?.popBackStack()
            }
        )
        Text(
            text = title,
            fontFamily = GraphicFontFamily,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold
        )
        Icon(
            painter = painterResource(id = R.drawable.icon_arrow_back),
            contentDescription = "",
            modifier = Modifier.alpha(0f)
        )
    }
}