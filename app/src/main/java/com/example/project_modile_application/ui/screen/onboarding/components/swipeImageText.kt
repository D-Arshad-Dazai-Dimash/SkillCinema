package com.example.project_modile_application.ui.screen.onboarding.components

import androidx.compose.foundation.Image
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SwipeImageText(page: OnboardingPage) {
    Column {
        Image(
            modifier = Modifier.fillMaxWidth().padding(vertical = 45.dp, horizontal = 39.dp)
                .align(Alignment.CenterHorizontally)
                .size(280.dp,180.dp),
            painter = painterResource(page.imageRes),
            contentDescription = null
        )

        Text(
            modifier = Modifier.padding(start = 26.dp),
            text = page.description,
            fontSize = 32.sp,
            fontWeight = FontWeight.W500,
        )
    }
}