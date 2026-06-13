package com.example.footballstore.ui.screens.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.footballstore.presentation.products.ProductViewModel
import com.example.footballstore.ui.components.EmptyState
import com.example.footballstore.ui.components.ErrorState
import com.example.footballstore.ui.components.LoadingState
import com.example.footballstore.ui.state.CategoryUiState
import com.example.footballstore.viewmodel.CategoryViewModel

@Composable
fun CategoryShopScreen(
    categoryViewModel: CategoryViewModel,
    productViewModel: ProductViewModel,
    onOpenCategory: (Int?) -> Unit,
    innerPadding: PaddingValues
) {
    val uiState by categoryViewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        Column(
            modifier = Modifier
                .statusBarsPadding()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                "Categorias",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                "Explora el catalogo por seccion y entra al listado ya filtrado.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 4.dp)
            )
            Button(
                onClick = { onOpenCategory(null) },
                modifier = Modifier.padding(top = 12.dp)
            ) {
                Text("Ver todo el catalogo")
            }
        }

        when (val state = uiState) {
            is CategoryUiState.Loading -> LoadingState()
            is CategoryUiState.Error -> ErrorState(
                message = state.message,
                onRetry = categoryViewModel::loadCategories
            )
            is CategoryUiState.Empty -> EmptyState(
                title = "Sin categorias",
                message = "Todavia no hay categorias disponibles."
            )
            is CategoryUiState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    contentPadding = PaddingValues(top = 16.dp, bottom = 120.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(state.categories, key = { it.id }) { category ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(20.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = category.name,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = category.description,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Text(
                                    text = "${productViewModel.getProductsCountByCategory(category.id)} productos disponibles",
                                    color = MaterialTheme.colorScheme.primary,
                                    style = MaterialTheme.typography.bodySmall,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Button(
                                    onClick = { onOpenCategory(category.id) },
                                    modifier = Modifier.padding(top = 4.dp)
                                ) {
                                    Text("Ver productos")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
