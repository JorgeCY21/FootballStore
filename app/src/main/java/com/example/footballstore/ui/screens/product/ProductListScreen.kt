package com.example.footballstore.ui.screens.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.footballstore.ui.components.CategoryFilterChips
import com.example.footballstore.ui.components.EmptyState
import com.example.footballstore.ui.components.ErrorState
import com.example.footballstore.ui.components.FootballSearchBar
import com.example.footballstore.ui.components.LoadingState
import com.example.footballstore.ui.components.ProductCard
import com.example.footballstore.ui.state.ProductUiState
import com.example.footballstore.viewmodel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(
    productViewModel: ProductViewModel,
    onNavigateToDetail: (Int) -> Unit,
    snackbarHostState: SnackbarHostState,
    innerPadding: PaddingValues
) {
    val uiState by productViewModel.uiState.collectAsStateWithLifecycle()
    val categories by productViewModel.categories.collectAsStateWithLifecycle()
    val query by productViewModel.searchQuery.collectAsStateWithLifecycle()
    val selectedCategoryId by productViewModel.selectedCategoryIdFlow.collectAsStateWithLifecycle()
    val message by productViewModel.message.collectAsStateWithLifecycle()

    LaunchedEffect(message) {
        if (!message.isNullOrBlank()) {
            snackbarHostState.showSnackbar(message!!)
            productViewModel.clearMessage()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(innerPadding)
    ) {
        TopAppBar(
            title = {
                Column {
                    Text("Football Store", fontWeight = FontWeight.Bold)
                    Text(
                        "Encuentra camisetas, botines y accesorios para tu proximo partido",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        )

        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            FootballSearchBar(
                query = query,
                onQueryChange = productViewModel::onSearchQueryChange,
                modifier = Modifier.padding(top = 8.dp, bottom = 12.dp)
            )
            CategoryFilterChips(
                categories = categories,
                selectedCategoryId = selectedCategoryId,
                onCategorySelected = productViewModel::onCategorySelected,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            when (val state = uiState) {
                is ProductUiState.Loading -> LoadingState()
                is ProductUiState.Error -> ErrorState(
                    message = state.message,
                    onRetry = productViewModel::loadProducts
                )
                is ProductUiState.Empty -> EmptyState(
                    title = "Sin resultados",
                    message = "Prueba con otra categoria o un nombre diferente."
                )
                is ProductUiState.Success -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(bottom = 120.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        items(state.products, key = { it.id }) { product ->
                            ProductCard(
                                product = product,
                                categoryName = productViewModel.getCategoryName(product.categoryId),
                                onView = { onNavigateToDetail(product.id) },
                                onAddToCart = { productViewModel.addToCart(product.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}
