package com.example.footballstore.data.repository

import com.example.footballstore.data.model.Category

interface CategoryRepository {
    fun getCategories(): List<Category>
    fun getCategoryById(id: Int): Category?
    fun addCategory(category: Category)
    fun updateCategory(category: Category)
    fun deleteCategory(id: Int)
}
