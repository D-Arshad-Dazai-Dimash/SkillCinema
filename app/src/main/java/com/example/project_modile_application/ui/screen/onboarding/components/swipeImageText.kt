package com.example.project_modile_application.ui.screen.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


@Composable
fun SwipeImageText(page: OnboardingPage) {
    Column {
        Image(
            painter = painterResource(page.imageRes),
            contentDescription = null
        )

        Text(
            text = page.description,
            fontSize = 32.sp,
            fontWeight = FontWeight.W500
        )
    }
}