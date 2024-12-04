package com.example.project_modile_application.presentation.ui.screen.filtrsetting.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project_modile_application.R
import com.example.project_modile_application.domain.dataclasses.searchPage.IdSearchPageParameter
import com.example.project_modile_application.presentation.ui.font.GraphicFontFamily

@Composable
fun SearchFilter(string: String) {
    var searchText by remember { mutableStateOf(TextFieldValue("")) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(top = 20.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(40.dp))
            .background(Color.LightGray, RoundedCornerShape(10.dp))
    ) {
        Image(
            painter = painterResource(R.drawable.icon),
            contentDescription = "icon",
            modifier = Modifier
                .weight(1f)
                .padding(start = 10.dp)
        )

        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            placeholder = { Text("Введите $string") },
            modifier = Modifier
                .weight(5f)
                .fillMaxHeight()
                .clip(RoundedCornerShape(40.dp))
                .background(Color.Transparent),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true
        )

    }
}


@Composable
fun TopInfo(string: String, dp: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { /* Handle back action */ }) {
            Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "Previous")
        }
        Spacer(modifier = Modifier.padding(horizontal = dp.dp))
        Text(text = string, fontSize = 16.sp, fontWeight = FontWeight.W600)
    }

}

@Composable
fun CountrySelectionScreen(title: String, list: List<IdSearchPageParameter>, onItemSelected: (IdSearchPageParameter) -> Unit) {
    var searchText by remember { mutableStateOf("") }
    val filteredCountries = list.filter {
        it.value?.contains(searchText, ignoreCase = true) == true
    }

    Column(modifier = Modifier.fillMaxSize()) {

        BasicTextField(
            value = searchText,
            onValueChange = { searchText = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
                .height(40.dp)
                .clip(RoundedCornerShape(100.dp))
                .background(Color(0x66B5B5C9)),
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Icon(Icons.Default.Search, contentDescription = "Поиск")
                    Box(modifier = Modifier.weight(1f)) {
                        if (searchText.isEmpty()) {
                            Text(
                                text = "Введите $title",
                                color = Color(0xFF838390),
                                fontSize = 14.sp,
                                fontFamily = GraphicFontFamily,
                                fontWeight = FontWeight.Medium
                            )
                        }
                        innerTextField()
                    }
                }
            },
            singleLine = true
        )

        //add it here

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(filteredCountries) { country ->
                CountryListItem(
                    countryName = country.value,
                    onClick = {onItemSelected(country)})
                Divider(color = Color.LightGray, thickness = 1.dp)
            }
        }
    }
}

@Composable
fun CountryListItem(countryName: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(8.dp)
    ) {
        Text(
            text = countryName,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
