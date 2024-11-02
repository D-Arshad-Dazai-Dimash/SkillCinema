package com.example.project_modile_application.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.project_modile_application.presentation.ui.screen.BottomNavigation


@Composable
fun MainComposable() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation(navController)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .shadow(
                    elevation = 8.dp,
                    shape = RectangleShape,
                    ambientColor = Color(0xFF3D3BFF),
                    spotColor = Color(0xFF3D3BFF)
                )
                .background(color = Color.White)
        ) {
            NavigationGraph(navController)
        }
    }
}