package com.example.footballstore.data.repository

import com.example.footballstore.data.model.Product

interface ProductRepository {
    fun getProducts(): List<Product>
    fun getProductById(id: Int): Product?
    fun addProduct(product: Product)
    fun updateProduct(product: Product)
    fun deleteProduct(id: Int)
    fun searchProducts(query: String, categoryId: Int?): List<Product>
}
