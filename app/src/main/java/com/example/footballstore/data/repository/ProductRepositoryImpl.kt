package com.example.footballstore.data.repository

import com.example.footballstore.data.datasource.FakeProductDataSource
import com.example.footballstore.domain.model.Product
import com.example.footballstore.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
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
}
