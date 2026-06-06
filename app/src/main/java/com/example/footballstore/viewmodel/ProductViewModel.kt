package com.example.footballstore.viewmodel

import androidx.lifecycle.ViewModel
import com.example.footballstore.data.model.CartItem
import com.example.footballstore.data.model.Category
import com.example.footballstore.data.model.Product
import com.example.footballstore.data.repository.CategoryRepository
import com.example.footballstore.data.repository.ProductRepository
import com.example.footballstore.ui.state.ProductUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProductViewModel(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProductUiState>(ProductUiState.Loading)
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedCategoryId = MutableStateFlow<Int?>(null)
    val selectedCategoryIdFlow: StateFlow<Int?> = _selectedCategoryId.asStateFlow()

    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> = _message.asStateFlow()

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems.asStateFlow()

    init {
        loadCategories()
        loadProducts()
    }

    fun loadProducts() {
        _uiState.value = ProductUiState.Loading
        runCatching {
            productRepository.searchProducts(_searchQuery.value, _selectedCategoryId.value)
        }.onSuccess { products ->
            _uiState.value = if (products.isEmpty()) {
                ProductUiState.Empty
            } else {
                ProductUiState.Success(
                    products = products,
                    query = _searchQuery.value,
                    selectedCategoryId = _selectedCategoryId.value
                )
            }
        }.onFailure { error ->
            _uiState.value = ProductUiState.Error(
                error.message ?: "No se pudo cargar el catalogo de productos."
            )
        }
    }

    fun loadCategories() {
        _categories.value = categoryRepository.getCategories()
    }

    fun getProductById(id: Int): Product? = productRepository.getProductById(id)

    fun getCategoryName(categoryId: Int): String {
        return _categories.value.firstOrNull { it.id == categoryId }?.name ?: "Sin categoria"
    }

    fun getProductsCountByCategory(categoryId: Int): Int {
        return productRepository.getProducts().count { it.categoryId == categoryId }
    }

    fun addProduct(
        name: String,
        price: String,
        description: String,
        imageUrl: String,
        categoryId: Int?
    ): Boolean {
        val validationMessage = validateProduct(name, price, description, imageUrl, categoryId)
        if (validationMessage != null) {
            _message.value = validationMessage
            return false
        }

        val newId = (productRepository.getProducts().maxOfOrNull { it.id } ?: 0) + 1
        val product = Product(
            id = newId,
            name = name.trim(),
            price = price.toDouble(),
            description = description.trim(),
            imageUrl = imageUrl.trim(),
            categoryId = categoryId ?: return false
        )

        productRepository.addProduct(product)
        loadProducts()
        _message.value = "Producto creado correctamente."
        return true
    }

    fun updateProduct(
        id: Int,
        name: String,
        price: String,
        description: String,
        imageUrl: String,
        categoryId: Int?
    ): Boolean {
        val validationMessage = validateProduct(name, price, description, imageUrl, categoryId)
        if (validationMessage != null) {
            _message.value = validationMessage
            return false
        }

        val product = Product(
            id = id,
            name = name.trim(),
            price = price.toDouble(),
            description = description.trim(),
            imageUrl = imageUrl.trim(),
            categoryId = categoryId ?: return false
        )

        productRepository.updateProduct(product)
        loadProducts()
        _message.value = "Producto actualizado correctamente."
        return true
    }

    fun deleteProduct(id: Int) {
        productRepository.deleteProduct(id)
        loadProducts()
        _message.value = "Producto eliminado correctamente."
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
        applyFilters()
    }

    fun onCategorySelected(categoryId: Int?) {
        _selectedCategoryId.value = categoryId
        applyFilters()
    }

    fun applyFilters() {
        val filteredProducts = productRepository.searchProducts(
            _searchQuery.value,
            _selectedCategoryId.value
        )
        _uiState.value = if (filteredProducts.isEmpty()) {
            ProductUiState.Empty
        } else {
            ProductUiState.Success(
                products = filteredProducts,
                query = _searchQuery.value,
                selectedCategoryId = _selectedCategoryId.value
            )
        }
    }

    fun hasProductsInCategory(categoryId: Int): Boolean {
        return productRepository.getProducts().any { it.categoryId == categoryId }
    }

    fun addToCart(productId: Int) {
        val product = productRepository.getProductById(productId) ?: return
        _cartItems.update { currentItems ->
            val existingItem = currentItems.firstOrNull { it.product.id == productId }
            if (existingItem == null) {
                currentItems + CartItem(product = product, quantity = 1)
            } else {
                currentItems.map { item ->
                    if (item.product.id == productId) {
                        item.copy(quantity = item.quantity + 1)
                    } else {
                        item
                    }
                }
            }
        }
        _message.value = "${product.name} agregado al carrito."
    }

    fun increaseCartItem(productId: Int) {
        _cartItems.update { currentItems ->
            currentItems.map { item ->
                if (item.product.id == productId) {
                    item.copy(quantity = item.quantity + 1)
                } else {
                    item
                }
            }
        }
    }

    fun decreaseCartItem(productId: Int) {
        _cartItems.update { currentItems ->
            currentItems.mapNotNull { item ->
                when {
                    item.product.id != productId -> item
                    item.quantity > 1 -> item.copy(quantity = item.quantity - 1)
                    else -> null
                }
            }
        }
    }

    fun removeFromCart(productId: Int) {
        _cartItems.update { currentItems ->
            currentItems.filterNot { it.product.id == productId }
        }
    }

    fun getCartTotal(): Double {
        return _cartItems.value.sumOf { it.product.price * it.quantity }
    }

    fun getCartItemsCount(): Int {
        return _cartItems.value.sumOf { it.quantity }
    }

    fun checkout(): Boolean {
        if (_cartItems.value.isEmpty()) {
            _message.value = "Tu carrito esta vacio."
            return false
        }
        _cartItems.value = emptyList()
        _message.value = "Compra realizada con exito. Gracias por tu pedido."
        return true
    }

    fun clearMessage() {
        _message.update { null }
    }

    private fun validateProduct(
        name: String,
        price: String,
        description: String,
        imageUrl: String,
        categoryId: Int?
    ): String? {
        if (name.isBlank()) return "El nombre del producto es obligatorio."
        val parsedPrice = price.toDoubleOrNull()
            ?: return "El precio debe ser un numero valido."
        if (parsedPrice <= 0.0) return "El precio debe ser mayor a 0."
        if (description.isBlank()) return "La descripcion es obligatoria."
        if (imageUrl.isBlank()) return "La imagen del producto es obligatoria."
        if (categoryId == null) return "Debes seleccionar una categoria."
        if (categoryRepository.getCategoryById(categoryId) == null) {
            return "La categoria seleccionada no existe."
        }
        return null
    }
}
