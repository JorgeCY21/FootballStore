package com.example.footballstore.data.repository

import com.example.footballstore.domain.model.Product
import com.example.footballstore.domain.repository.ProductRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeProductRepository @Inject constructor() : ProductRepository {

    private val products = mutableListOf(
        Product(
            id = 901,
            name = "Balon de practica academico",
            price = 89.0,
            stock = 12,
            description = "Repositorio fake para pruebas de ViewModel y escenarios controlados.",
            imageUrl = "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/7f550da377f041a4aef78883ff3582bf_9366/PELOTA_CLUB_EPP_Multicolor_JW4011_00_plp_standard.jpg",
            categoryId = 3
        ),
        Product(
            id = 902,
            name = "Camiseta fake de entrenamiento",
            price = 119.0,
            stock = 5,
            description = "Implementacion fake separada de la fuente local principal.",
            imageUrl = "https://assets.adidas.com/images/w_383%2Ch_383%2Cf_auto%2Cq_auto%2Cfl_lossy%2Cc_fill%2Cg_auto/21283e1da2fc4df58f59c38a16038a49_9366/Camiseta_Local_Seleccion_Peruana_26_Blanco_JL8651_000_plp_model.jpg",
            categoryId = 1
        )
    )

    override fun getProducts(): List<Product> = products.toList()

    override fun getProductById(id: Int): Product? = products.firstOrNull { it.id == id }

    override fun addProduct(product: Product) {
        products.add(product)
    }

    override fun updateProduct(product: Product) {
        val index = products.indexOfFirst { it.id == product.id }
        if (index >= 0) {
            products[index] = product
        }
    }

    override fun deleteProduct(id: Int) {
        products.removeAll { it.id == id }
    }
}
