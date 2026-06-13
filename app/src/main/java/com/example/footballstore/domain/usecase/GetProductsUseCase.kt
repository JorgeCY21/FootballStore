package com.example.footballstore.domain.usecase

import com.example.footballstore.domain.model.Product
import com.example.footballstore.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(query: String = "", categoryId: Int? = null): List<Product> {
        return productRepository.getProducts().filter { product ->
            val matchesQuery = query.isBlank() || product.name.contains(query, ignoreCase = true)
            val matchesCategory = categoryId == null || product.categoryId == categoryId
            matchesQuery && matchesCategory
        }
    }
}
