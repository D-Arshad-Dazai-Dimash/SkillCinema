package com.example.project_modile_application.ui.screen.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.project_modile_application.R
import com.example.project_modile_application.navigation.Screen
import com.example.project_modile_application.ui.screen.onboarding.components.SwipeImageText
import com.example.project_modile_application.ui.screen.onboarding.components.images


@Composable
fun OnBoardingScreen(navController: NavController) {
    val pagerState = rememberPagerState { images.size }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 38.dp, bottom = 166.76.dp, start = 26.dp, end = 26.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.vector_1),
                contentDescription = ""
            )
            TextButton(onClick = {
                navController.navigate(Screen.Home.route)
            }) {
                Text("Пропустить", color = Color.LightGray)
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            SwipeImageText(page = images[page])
        }
        //three dot
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 56.dp, start = 26.dp)
        ) {
            repeat(images.size) { index ->
                val color = if (index == pagerState.currentPage) Color.Black else Color.LightGray
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .padding(4.dp)
                        .background(color, CircleShape)
                )
            }
        }
    }
}