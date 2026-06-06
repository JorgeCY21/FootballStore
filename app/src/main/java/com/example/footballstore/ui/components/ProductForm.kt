package com.example.footballstore.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.footballstore.data.model.Category

@Composable
fun ProductForm(
    categories: List<Category>,
    submitLabel: String,
    onSubmit: (String, String, String, String, Int?) -> Boolean,
    modifier: Modifier = Modifier,
    initialName: String = "",
    initialPrice: String = "",
    initialDescription: String = "",
    initialImageUrl: String = "",
    initialCategoryId: Int? = null
) {
    var name by rememberSaveable { mutableStateOf(initialName) }
    var price by rememberSaveable { mutableStateOf(initialPrice) }
    var description by rememberSaveable { mutableStateOf(initialDescription) }
    var imageUrl by rememberSaveable { mutableStateOf(initialImageUrl) }
    var selectedCategoryId by rememberSaveable { mutableStateOf(initialCategoryId) }
    var expanded by remember { mutableStateOf(false) }

    val selectedCategoryName = categories.firstOrNull { it.id == selectedCategoryId }?.name ?: ""

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Nombre") },
            singleLine = true
        )
        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Precio") },
            singleLine = true
        )
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Descripcion") },
            minLines = 3
        )
        OutlinedTextField(
            value = imageUrl,
            onValueChange = { imageUrl = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("URL de imagen") },
            supportingText = {
                Text(
                    "Usa una URL publica estable para mostrar la imagen del producto.",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        )
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = selectedCategoryName,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = true },
                label = { Text("Categoria") },
                supportingText = { Text("Toca el campo para elegir una categoria") }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                categories.forEach { category ->
                    DropdownMenuItem(
                        text = { Text(category.name) },
                        onClick = {
                            selectedCategoryId = category.id
                            expanded = false
                        }
                    )
                }
            }
        }
        Button(
            onClick = {
                onSubmit(
                    name,
                    price,
                    description,
                    imageUrl,
                    selectedCategoryId
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(submitLabel)
        }
    }
}
