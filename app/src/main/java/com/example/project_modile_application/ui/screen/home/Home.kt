package com.example.project_modile_application.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.project_modile_application.R
import com.example.project_modile_application.ui.screen.home.components.Category

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomePrev() {
    Home()
}

@Composable
fun Home() {
    Column(Modifier.padding(start = 26.dp, end = 26.dp, top = 55.dp)) {
        Image(
            painter = painterResource(R.drawable.skillcinema),
            contentDescription = "",
            modifier = Modifier
                .padding(bottom = 10.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState(0)),
        ) {
            Category()
            Category()
            Category()
            Category()
        }

    }

}