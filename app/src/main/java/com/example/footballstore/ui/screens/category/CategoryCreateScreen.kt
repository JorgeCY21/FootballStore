package com.example.footballstore.ui.screens.category

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.footballstore.ui.components.CategoryForm
import com.example.footballstore.viewmodel.CategoryViewModel
import com.example.footballstore.presentation.products.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryCreateScreen(
    categoryViewModel: CategoryViewModel,
    productViewModel: ProductViewModel,
    onBack: () -> Unit,
    onCreated: () -> Unit,
    innerPadding: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        TopAppBar(
            title = { Text("Nueva categoria", fontWeight = FontWeight.Bold) },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                }
            }
        )
        CategoryForm(
            submitLabel = "Guardar categoria",
            onSubmit = { name, description ->
                val created = categoryViewModel.addCategory(name, description)
                if (created) {
                    productViewModel.loadCategories()
                    onCreated()
                }
                created
            },
            modifier = Modifier.padding(16.dp)
        )
    }
}
