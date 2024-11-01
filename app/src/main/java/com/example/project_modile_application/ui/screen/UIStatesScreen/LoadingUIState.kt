package com.example.project_modile_application.ui.screen.UIStatesScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun LoadingUIState() {
    Box(
        modifier = Modifier.fillMaxSize()
        ,contentAlignment = Alignment.Center
    )
    {

        CircularProgressIndicator()

    }
}