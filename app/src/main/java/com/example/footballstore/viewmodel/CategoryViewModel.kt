package com.example.footballstore.viewmodel

import androidx.lifecycle.ViewModel
import com.example.footballstore.data.model.Category
import com.example.footballstore.data.repository.CategoryRepository
import com.example.footballstore.ui.state.CategoryUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<CategoryUiState>(CategoryUiState.Loading)
    val uiState: StateFlow<CategoryUiState> = _uiState.asStateFlow()

    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> = _message.asStateFlow()

    init {
        loadCategories()
    }

    fun loadCategories() {
        _uiState.value = CategoryUiState.Loading
        runCatching {
            categoryRepository.getCategories()
        }.onSuccess { categories ->
            _uiState.value = if (categories.isEmpty()) {
                CategoryUiState.Empty
            } else {
                CategoryUiState.Success(categories)
            }
        }.onFailure { error ->
            _uiState.value = CategoryUiState.Error(
                error.message ?: "No se pudieron cargar las categorias."
            )
        }
    }

    fun getCategoryById(id: Int): Category? = categoryRepository.getCategoryById(id)

    fun getCategories(): List<Category> = categoryRepository.getCategories()

    fun addCategory(name: String, description: String): Boolean {
        val validationMessage = validateCategory(name, description)
        if (validationMessage != null) {
            _message.value = validationMessage
            return false
        }

        val newId = (categoryRepository.getCategories().maxOfOrNull { it.id } ?: 0) + 1
        categoryRepository.addCategory(
            Category(
                id = newId,
                name = name.trim(),
                description = description.trim()
            )
        )
        loadCategories()
        _message.value = "Categoria creada correctamente."
        return true
    }

    fun updateCategory(id: Int, name: String, description: String): Boolean {
        val validationMessage = validateCategory(name, description)
        if (validationMessage != null) {
            _message.value = validationMessage
            return false
        }

        categoryRepository.updateCategory(
            Category(
                id = id,
                name = name.trim(),
                description = description.trim()
            )
        )
        loadCategories()
        _message.value = "Categoria actualizada correctamente."
        return true
    }

    fun deleteCategory(id: Int) {
        categoryRepository.deleteCategory(id)
        loadCategories()
        _message.value = "Categoria eliminada correctamente."
    }

    fun clearMessage() {
        _message.update { null }
    }

    fun setMessage(message: String) {
        _message.value = message
    }

    private fun validateCategory(name: String, description: String): String? {
        if (name.isBlank()) return "El nombre de la categoria es obligatorio."
        if (description.isBlank()) return "La descripcion de la categoria es obligatoria."
        return null
    }
}
