package com.example.footballstore.data.repository

import com.example.footballstore.data.datasource.FakeProductDataSource
import com.example.footballstore.data.model.Product

class ProductRepositoryImpl(
    private val dataSource: FakeProductDataSource
) : ProductRepository {

    override fun getProducts(): List<Product> = dataSource.getProducts()

    override fun getProductById(id: Int): Product? = dataSource.getProductById(id)

    override fun addProduct(product: Product) {
        dataSource.addProduct(product)
    }

    override fun updateProduct(product: Product) {
        dataSource.updateProduct(product)
    }

    override fun deleteProduct(id: Int) {
        dataSource.deleteProduct(id)
    }

    override fun searchProducts(query: String, categoryId: Int?): List<Product> {
        return dataSource.getProducts().filter { product ->
            val matchesQuery = query.isBlank() || product.name.contains(query, ignoreCase = true)
            val matchesCategory = categoryId == null || product.categoryId == categoryId
            matchesQuery && matchesCategory
        }
    }
}
