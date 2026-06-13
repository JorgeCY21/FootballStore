package com.example.footballstore.domain.model

data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val stock: Int,
    val description: String,
    val imageUrl: String,
    val categoryId: Int
)
