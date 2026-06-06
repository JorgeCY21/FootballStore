package com.example.footballstore.ui.state

import com.example.footballstore.data.model.Category

sealed class CategoryUiState {
    data object Loading : CategoryUiState()
    data class Success(val categories: List<Category>) : CategoryUiState()
    data class Error(val message: String) : CategoryUiState()
    data object Empty : CategoryUiState()
}
