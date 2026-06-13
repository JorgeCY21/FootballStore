package com.example.footballstore.domain.repository

import com.example.footballstore.domain.model.Product

interface ProductRepository {
    fun getProducts(): List<Product>
    fun getProductById(id: Int): Product?
    fun addProduct(product: Product)
    fun updateProduct(product: Product)
    fun deleteProduct(id: Int)
}
