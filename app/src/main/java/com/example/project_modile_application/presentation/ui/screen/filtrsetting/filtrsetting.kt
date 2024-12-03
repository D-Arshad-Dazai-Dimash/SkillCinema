package com.example.project_modile_application.presentation.ui.screen.filtrsetting

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.project_modile_application.presentation.navigation.Screen
import com.example.project_modile_application.presentation.ui.screen.filtrsetting.components.TopInfo

@Preview(showBackground = true)
@Composable
fun SearchSettingsScreen(navController: NavController = rememberNavController()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 22.dp, vertical = 40.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
            verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { /* Handle back action */ }) {
                Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "Previous")
            }
            Spacer(modifier = Modifier.padding(horizontal = 34.dp))
            Text(text = "Настройки поиска", fontSize = 16.sp, fontWeight = FontWeight.W600)
        }
        Spacer(modifier = Modifier.height(16.dp))
        CategorySelector()
        Spacer(modifier = Modifier.height(16.dp))
        FilterOption(title = "Страна", value = "Россия", navController = navController, destination = Screen.FilterCountry.route)
        Divider(color = Color.LightGray, thickness = 1.dp)
        FilterOption(title = "Жанр", value = "Комедия", navController = navController, destination = Screen.FilterGenre.route)
        Divider(color = Color.LightGray, thickness = 1.dp)
        YearRangeSelector(startYear = 1998, endYear = 2017,  navController = navController, destination = Screen.FilterPeriod.route)
        Divider(color = Color.LightGray, thickness = 1.dp)
        RatingSlider()
        Divider(color = Color.LightGray, thickness = 1.dp)
        SortSelector()
        Divider(color = Color.LightGray, thickness = 1.dp)
        NotWatchedCheckbox()
    }
}

@Composable
fun FilterOption(title: String, value: String, navController: NavController, destination: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                navController.navigate(destination) // Навигация на другой экран
            },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title, color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.W400)
        Text(text = value, color = Color.Gray, fontSize = 14.sp, fontWeight = FontWeight.W400)
    }
}

@Composable
fun YearRangeSelector(startYear: Int, endYear: Int,navController: NavController, destination: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp).clickable {
                navController.navigate(destination) // Навигация на другой экран
            },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Год", color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.W400)
        Text("с $startYear до $endYear", color = Color.Gray, fontSize = 14.sp, fontWeight = FontWeight.W400)
    }
}

@Composable
fun RatingSlider() {
    var range by remember { mutableStateOf(1f..10f) } // Изначальный диапазон

    val displayText = if (range.start == 1f && range.endInclusive == 10f) {
        "любой"
    } else {
        "от ${range.start.toInt()} до ${range.endInclusive.toInt()}"
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Рейтинг",
                color = Color.Black
            )
            Text(
                text = displayText,
                color = Color.Gray
            )
        }
        RangeSlider(
            value = range,
            onValueChange = {
                val start = it.start.coerceAtLeast(1f) // Минимум для левого
                val end = it.endInclusive.coerceAtMost(10f) // Максимум для правого

                // Проверка на пересечение
                if (start < end) {
                    range = start..end
                }
            },
            valueRange = 1f..10f, // Полный диапазон
            colors = SliderDefaults.colors(
                thumbColor = Color.White,
                activeTrackColor = Color(0xFF3D3BFF),
                inactiveTrackColor = Color.LightGray
            )
        )
    }
}

@Composable
fun NotWatchedCheckbox() {
    var checked by remember { mutableStateOf(false) }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(Icons.Default.Clear, contentDescription = null)
        Text(
            text = "Не просмотрен",
            modifier = Modifier
                .clickable { checked = !checked }
                .padding(start = 8.dp),
            color = if (checked) Color(0xFF3D3BFF) else Color.Black
        )
    }
}

@Composable
fun CategorySelector() {
    var selectedCategory by remember { mutableStateOf("Все") }
    val categories = listOf("Все", "Фильмы", "Сериалы")
Column {
    Text(text = "Показывать", fontWeight = FontWeight.W400, fontSize = 12.sp, color = Color.Gray)


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .height(40.dp), // Высота всех кнопок
        horizontalArrangement = Arrangement.Center
    ) {
        categories.forEachIndexed { index, category ->
            val isSelected = selectedCategory == category
            val shape = when (index) {
                0 -> RoundedCornerShape(topStart = 20.dp, bottomStart = 20.dp) // Левая кнопка
                categories.lastIndex -> RoundedCornerShape(topEnd = 20.dp, bottomEnd = 20.dp) // Правая кнопка
                else -> RoundedCornerShape(0.dp) // Центральная кнопка
            }

            androidx.compose.material3.Surface(
                shape = shape,
                color = if (isSelected) Color(0xFF3D3BFF) else Color.Transparent,
                border = if (isSelected) null else BorderStroke(1.dp, Color.Black),
                modifier = Modifier
                    .weight(1f)
                    .clickable { selectedCategory = category }
            ) {
                Text(
                    text = category,
                    color = if (isSelected) Color.White else Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
        }
    }
    }
}
@Composable
fun SortSelector() {
    var selectedSort by remember { mutableStateOf("Дата") }
    val sorts = listOf("Дата", "Популярность", "Рейтинг")
Column {
    Text(text = "Сортировать", fontWeight = FontWeight.W400, fontSize = 12.sp, color = Color.Gray)


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .height(40.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        sorts.forEachIndexed { index, sort ->
            val isSelected = selectedSort == sort

            val shape = when (index) {
                0 -> RoundedCornerShape(topStart = 20.dp, bottomStart = 20.dp) // Левая кнопка
                sorts.lastIndex -> RoundedCornerShape(topEnd = 20.dp, bottomEnd = 20.dp) // Правая кнопка
                else -> RoundedCornerShape(0.dp) // Центральная кнопка
            }

            androidx.compose.material3.Surface(
                shape = shape,
                color = if (isSelected) Color(0xFF3D3BFF) else Color.Transparent,
                border = if (isSelected) null else BorderStroke(1.dp, Color.Black),
                modifier = Modifier
                    .weight(1f)
                    .clickable { selectedSort = sort }
            ) {
                Text(
                    text = sort,
                    color = if (isSelected) Color.White else Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
        }
    }
}
}