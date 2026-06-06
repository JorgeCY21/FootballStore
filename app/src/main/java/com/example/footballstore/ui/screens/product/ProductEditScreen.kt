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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.footballstore.ui.components.EmptyState
import com.example.footballstore.ui.components.ProductForm
import com.example.footballstore.viewmodel.CategoryViewModel
import com.example.footballstore.viewmodel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductEditScreen(
    productId: Int,
    productViewModel: ProductViewModel,
    categoryViewModel: CategoryViewModel,
    onBack: () -> Unit,
    onUpdated: () -> Unit,
    innerPadding: PaddingValues
) {
    val product = productViewModel.getProductById(productId)
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
            title = { Text("Editar producto", fontWeight = FontWeight.Bold) },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                }
            }
        )

        if (product == null) {
            EmptyState(
                title = "Producto no disponible",
                message = "No es posible editar un producto que ya no existe.",
                modifier = Modifier.fillMaxSize()
            )
            return
        }

        ProductForm(
            categories = categories,
            submitLabel = "Actualizar producto",
            onSubmit = { name, price, description, imageUrl, categoryId ->
                val updated = productViewModel.updateProduct(
                    id = product.id,
                    name = name,
                    price = price,
                    description = description,
                    imageUrl = imageUrl,
                    categoryId = categoryId
                )
                if (updated) {
                    onUpdated()
                }
                updated
            },
            modifier = Modifier.padding(16.dp),
            initialName = product.name,
            initialPrice = product.price.toString(),
            initialDescription = product.description,
            initialImageUrl = product.imageUrl,
            initialCategoryId = product.categoryId
        )
    }
}
