package com.example.footballstore.ui.screens.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.activity.ComponentActivity
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.footballstore.presentation.products.ProductViewModel
import com.example.footballstore.ui.components.CategoryFilterChips
import com.example.footballstore.ui.components.EmptyState
import com.example.footballstore.ui.components.ErrorState
import com.example.footballstore.ui.components.FootballSearchBar
import com.example.footballstore.ui.components.LoadingState
import com.example.footballstore.ui.components.ProductCard

@Composable
fun ProductListScreen(
    onNavigateToDetail: (Int) -> Unit,
    snackbarHostState: SnackbarHostState,
    innerPadding: PaddingValues
) {
    val activity = LocalContext.current as ComponentActivity
    val productViewModel: ProductViewModel = hiltViewModel(activity)
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
        Column(
            modifier = Modifier
                .statusBarsPadding()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                "Football Store",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                "Encuentra camisetas, botines y accesorios para tu proximo partido",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 4.dp)
            )
            FootballSearchBar(
                query = query,
                onQueryChange = productViewModel::onSearchQueryChange,
                modifier = Modifier.padding(top = 14.dp, bottom = 12.dp)
            )
            CategoryFilterChips(
                categories = categories,
                selectedCategoryId = selectedCategoryId,
                onCategorySelected = productViewModel::onCategorySelected,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            when {
                uiState.loading -> LoadingState()
                uiState.error != null -> ErrorState(
                    message = uiState.error.orEmpty(),
                    onRetry = productViewModel::loadProducts
                )
                uiState.products.isEmpty() -> EmptyState(
                    title = "Sin resultados",
                    message = "Prueba con otra categoria o un nombre diferente."
                )
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(top = 10.dp, bottom = 120.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        items(uiState.products, key = { it.id }) { product ->
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
