package com.example.footballstore.ui.screens.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.footballstore.ui.components.CategoryCard
import com.example.footballstore.ui.components.ConfirmDeleteDialog
import com.example.footballstore.ui.components.EmptyState
import com.example.footballstore.ui.components.ErrorState
import com.example.footballstore.ui.components.LoadingState
import com.example.footballstore.ui.state.CategoryUiState
import com.example.footballstore.viewmodel.CategoryViewModel
import com.example.footballstore.presentation.products.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryListScreen(
    categoryViewModel: CategoryViewModel,
    productViewModel: ProductViewModel,
    onBack: () -> Unit,
    onCreate: () -> Unit,
    onEdit: (Int) -> Unit,
    innerPadding: PaddingValues
) {
    val uiState by categoryViewModel.uiState.collectAsStateWithLifecycle()
    val message by categoryViewModel.message.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    var categoryToDelete by remember { mutableIntStateOf(-1) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    LaunchedEffect(message) {
        if (!message.isNullOrBlank()) {
            snackbarHostState.showSnackbar(message!!)
            categoryViewModel.clearMessage()
            productViewModel.loadCategories()
            productViewModel.applyFilters()
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("Categorias", fontWeight = FontWeight.Bold)
                        Text(
                            "Gestiona las secciones del catalogo",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onCreate,
                text = { Text("Nueva categoria") },
                icon = { Icon(Icons.Default.Add, contentDescription = "Nueva categoria") }
            )
        }
    ) { padding ->
        when (val state = uiState) {
            is CategoryUiState.Loading -> LoadingState(modifier = Modifier.padding(padding))
            is CategoryUiState.Error -> ErrorState(
                message = state.message,
                onRetry = categoryViewModel::loadCategories,
                modifier = Modifier.padding(padding)
            )
            is CategoryUiState.Empty -> EmptyState(
                title = "Sin categorias",
                message = "Agrega categorias para organizar mejor tus productos.",
                modifier = Modifier.padding(padding)
            )
            is CategoryUiState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(horizontal = 16.dp),
                    contentPadding = PaddingValues(top = 16.dp, bottom = 96.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(state.categories, key = { it.id }) { category ->
                        CategoryCard(
                            category = category,
                            onEdit = { onEdit(category.id) },
                            onDelete = {
                                categoryToDelete = category.id
                                showDeleteDialog = true
                            }
                        )
                    }
                }
            }
        }
    }

    if (showDeleteDialog && categoryToDelete != -1) {
        ConfirmDeleteDialog(
            title = "Eliminar categoria",
            message = "Si la categoria tiene productos asociados, no se podra eliminar.",
            onConfirm = {
                if (productViewModel.hasProductsInCategory(categoryToDelete)) {
                    categoryViewModel.setMessage("No puedes eliminar una categoria con productos asociados.")
                } else {
                    categoryViewModel.deleteCategory(categoryToDelete)
                }
                showDeleteDialog = false
                categoryToDelete = -1
            },
            onDismiss = {
                showDeleteDialog = false
                categoryToDelete = -1
            }
        )
    }
}
