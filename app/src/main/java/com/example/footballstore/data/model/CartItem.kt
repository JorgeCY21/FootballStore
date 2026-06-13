package com.example.footballstore.data.model

import com.example.footballstore.domain.model.Product

data class CartItem(
    val product: Product,
    val quantity: Int
)
