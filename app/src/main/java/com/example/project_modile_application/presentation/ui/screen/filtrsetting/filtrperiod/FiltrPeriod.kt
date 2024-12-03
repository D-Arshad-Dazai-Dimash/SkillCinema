package com.example.project_modile_application.presentation.ui.screen.filtrsetting.filtrperiod

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.project_modile_application.presentation.ui.screen.filtrsetting.components.TopInfo

@Preview(showBackground = true)
@Composable
fun FilterPeriodScreen(navController: NavController= rememberNavController()) {
    var selectedYearFrom by remember { mutableStateOf<Int?>(null) }
    var selectedYearTo by remember { mutableStateOf<Int?>(null) }
    var startYearFrom by remember { mutableStateOf(1998) }
    var endYearFrom by remember { mutableStateOf(2009) }
    var startYearTo by remember { mutableStateOf(1998) }
    var endYearTo by remember { mutableStateOf(2009) }
    var errorMessage by remember { mutableStateOf<String?>(null) }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        TopInfo("Период",50)



        PeriodSelector(
            title = "Искать в период с",
            startYear = startYearFrom,
            endYear = endYearFrom,
            selectedYear = selectedYearFrom,
            onYearClick = { year ->
                selectedYearFrom = year
                validatePeriod(selectedYearFrom, selectedYearTo) { error ->
                    errorMessage = error
                }
            },
            onPrevious = {
                if (startYearFrom > 1786) {
                    startYearFrom -= 12
                    endYearFrom -= 12
                }
            },
            onNext = {
                if (endYearFrom < 2025) {
                    startYearFrom += 12
                    endYearFrom += 12
                }
            }
        )

        PeriodSelector(
            title = "Искать в период до",
            startYear = startYearTo,
            endYear = endYearTo,
            selectedYear = selectedYearTo,
            onYearClick = { year ->
                selectedYearTo = year
                validatePeriod(selectedYearFrom, selectedYearTo) { error ->
                    errorMessage = error
                }
            },
            onPrevious = {
                if (startYearTo > 1786) {
                    startYearTo -= 12
                    endYearTo -= 12
                }
            },
            onNext = {
                if (endYearTo < 2025) {
                    startYearTo += 12
                    endYearTo += 12
                }
            }
        )

        // Отображаем ошибку, если она есть
        errorMessage?.let {
            Text(
                text = it,
                color = Color.Red,
                modifier = Modifier.padding(8.dp)
            )
        }

        Button(
            onClick = { /* Логика обработки выбранных годов */ },
            enabled = errorMessage == null // Кнопка активна только если нет ошибок
        ) {
            Text("Выбрать")
        }
    }
}

// Валидация диапазона лет
private fun validatePeriod(
    selectedYearFrom: Int?,
    selectedYearTo: Int?,
    onValidationResult: (String?) -> Unit
) {
    if (selectedYearFrom != null && selectedYearTo != null && selectedYearFrom > selectedYearTo) {
        onValidationResult("Ошибка: Год начала не может быть больше года окончания.")
    } else {
        onValidationResult(null)
    }
}

@Composable
fun PeriodSelector(
    title: String,
    startYear: Int,
    endYear: Int,
    selectedYear: Int?,
    onYearClick: (Int) -> Unit,
    onPrevious: () -> Unit,
    onNext: () -> Unit
) {
    val color = Color(0xFF3D3BFF)

    Column {
        Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.W400,color=Color(0xFF838390))

        Box(
            modifier = Modifier
                .padding(top = 8.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                .padding(horizontal = 16.dp)
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "$startYear - $endYear",
                        color = Color.Blue,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W500
                    )

                    Row {
                        IconButton(onClick = onPrevious) {
                            Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "Previous")
                        }
                        IconButton(onClick = onNext) {
                            Icon(Icons.Default.KeyboardArrowRight, contentDescription = "Next")
                        }
                    }
                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    contentPadding = PaddingValues(top = 8.dp)
                ) {
                    val years = (startYear..endYear).toList()
                    items(years) { year ->
                        Text(
                            text = (year.toString()),
                            color = if (year == selectedYear) color else Color.Black,
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable { onYearClick(year) },
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp, fontWeight = FontWeight.W500
                        )
                    }
                }
            }
        }
    }
}