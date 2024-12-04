package com.example.project_modile_application.presentation.ui.screen.profilePage.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project_modile_application.R
import com.example.project_modile_application.domain.viewModels.RoomViewModel
import com.example.project_modile_application.presentation.ui.font.GraphicFontFamily

@Composable
fun NewCollectionButton(roomViewModel: RoomViewModel) {
    val showDialog = remember { mutableStateOf(false) }
    val newCollectionName = remember { mutableStateOf("") }

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = {
                Text(
                    text = "Новая коллекция",
                    fontFamily = GraphicFontFamily,
                    fontWeight = W600,
                    fontSize = 18.sp
                )
            },
            text = {
                Column {
                    Text(text = "Введите название коллекции:")
                    OutlinedTextField(
                        value = newCollectionName.value,
                        onValueChange = { newCollectionName.value = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (newCollectionName.value.isNotBlank()) {
                            roomViewModel.addCollection(newCollectionName.value)
                        }
                        newCollectionName.value = ""
                        showDialog.value = false
                    }
                ) {
                    Text("Создать")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        newCollectionName.value = ""
                        showDialog.value = false
                    }
                ) {
                    Text("Отмена")
                }
            }
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { showDialog.value = true }
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.plus_icon),
            contentDescription = "Add Collection",
            modifier = Modifier.size(24.dp),
            tint = Color.DarkGray
        )
        Text(
            text = "Создать свою коллекцию",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            fontFamily = GraphicFontFamily,
            fontWeight = W400,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}