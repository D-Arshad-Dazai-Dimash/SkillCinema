package com.example.project_modile_application.ui.screen.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SwipeImageText(page: OnboardingPage) {
    Column(modifier = Modifier.fillMaxWidth(),
    ) {
        Image(
            modifier = Modifier
                .size(360.dp, 300.dp)
                .padding(bottom = 67.24.dp, top = 44.dp)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(page.imageRes),
            contentDescription = null
        )

        Text(
            modifier = Modifier.padding(start=26.dp),
            text = page.description,
            fontSize = 32.sp,
            fontWeight = FontWeight.W500
        )

    }
}