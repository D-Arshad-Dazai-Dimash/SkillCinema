package com.example.project_modile_application.presentation.ui.screen.searchPageParameters.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project_modile_application.domain.dataclasses.searchPage.ApiMappedSearchPageParameter
import com.example.project_modile_application.presentation.ui.font.GraphicFontFamily

@Composable
fun FilterTab(
    tabs: List<ApiMappedSearchPageParameter>,
    title: String,
    onItemSelected: (ApiMappedSearchPageParameter) -> Unit
) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier
            .padding(vertical = 16.dp)
    ) {

        Text(
            modifier = Modifier
                .padding(
                    horizontal = 26.dp
                ),
            text = title,
            color = Color(color = 0XFF838390),
            fontFamily = GraphicFontFamily,
            fontSize = 12.sp,
            fontWeight = FontWeight.W400
        )
        Row(
            modifier = Modifier
                .padding(
                    top = 16.dp, start = 26.dp, end = 26.dp
                )
                .height(32.dp)
                .border(1.dp, Color.Black, RoundedCornerShape(50.dp))
                .clip(RoundedCornerShape(50.dp))
        ) {
            tabs.forEachIndexed { index, tab ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(
                            if (index == selectedTabIndex) Color.Blue else Color.White,
                        )
                        .clickable {
                            selectedTabIndex = index
                            onItemSelected(tab)
                        }
                        .padding(vertical = 4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = tab.uiValue,
                        color = if (index == selectedTabIndex) Color.White else Color.Black,
                        modifier = Modifier.padding(horizontal = 8.dp),
                        fontSize = 14.sp
                    )
                }
                VerticalDivider(color = Color.Black)
            }
        }
    }
}