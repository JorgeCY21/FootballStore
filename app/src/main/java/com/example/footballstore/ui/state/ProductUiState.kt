package com.example.footballstore.ui.state

import com.example.footballstore.data.model.Product

sealed class ProductUiState {
    data object Loading : ProductUiState()
    data class Success(
        val products: List<Product>,
        val query: String,
        val selectedCategoryId: Int?
    ) : ProductUiState()

    data class Error(val message: String) : ProductUiState()
    data object Empty : ProductUiState()
}
