package com.example.footballstore.data.repository

import com.example.footballstore.data.datasource.FakeCategoryDataSource
import com.example.footballstore.data.model.Category

class CategoryRepositoryImpl(
    private val dataSource: FakeCategoryDataSource
) : CategoryRepository {

    override fun getCategories(): List<Category> = dataSource.getCategories()

    override fun getCategoryById(id: Int): Category? = dataSource.getCategoryById(id)

    override fun addCategory(category: Category) {
        dataSource.addCategory(category)
    }

    override fun updateCategory(category: Category) {
        dataSource.updateCategory(category)
    }

    override fun deleteCategory(id: Int) {
        dataSource.deleteCategory(id)
    }
}
