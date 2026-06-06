package com.example.footballstore.ui.screens.product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.footballstore.ui.components.ProductForm
import com.example.footballstore.viewmodel.CategoryViewModel
import com.example.footballstore.viewmodel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductCreateScreen(
    productViewModel: ProductViewModel,
    categoryViewModel: CategoryViewModel,
    onBack: () -> Unit,
    onCreated: () -> Unit,
    innerPadding: PaddingValues
) {
    val categories by productViewModel.categories.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        productViewModel.loadCategories()
        categoryViewModel.loadCategories()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        TopAppBar(
            title = {
                Text(
                    "Nuevo producto",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                }
            }
        )
        ProductForm(
            categories = categories,
            submitLabel = "Guardar producto",
            onSubmit = { name, price, description, imageUrl, categoryId ->
                val saved = productViewModel.addProduct(
                    name = name,
                    price = price,
                    description = description,
                    imageUrl = imageUrl,
                    categoryId = categoryId
                )
                if (saved) {
                    categoryViewModel.loadCategories()
                    onCreated()
                }
                saved
            },
            modifier = Modifier.padding(16.dp)
        )
    }
}
