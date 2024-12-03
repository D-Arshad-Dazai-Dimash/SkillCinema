package com.example.project_modile_application.presentation.ui.screen.filtrsetting.filtrcountry

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.project_modile_application.R
import com.example.project_modile_application.domain.dataclasses.Country
import com.example.project_modile_application.domain.dataclasses.Movie
import com.example.project_modile_application.presentation.ui.screen.filtrsetting.components.CountrySelectionScreen
import com.example.project_modile_application.presentation.ui.screen.filtrsetting.components.SearchFilter
import com.example.project_modile_application.presentation.ui.screen.filtrsetting.components.TopInfo
import java.nio.file.DirectoryStream
import java.util.logging.Filter

@Preview(showBackground = true)
@Composable
fun FilterCountryScreen(navController: NavController= rememberNavController()){
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(26.dp)
    ) {
        TopInfo("Страна",45)
            //SearchFilter("страну")
        CountrySelectionScreen("страну",listOf("Россия", "Великобритания", "Германия", "США", "Франция"))

    }



}