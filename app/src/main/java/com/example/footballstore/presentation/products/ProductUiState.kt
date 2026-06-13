package com.example.footballstore.presentation.products

import com.example.footballstore.domain.model.Product

data class ProductUiState(
    val loading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: String? = null,
    val query: String = "",
    val selectedCategoryId: Int? = null
)
