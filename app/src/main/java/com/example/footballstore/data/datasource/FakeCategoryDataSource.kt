package com.example.footballstore.data.datasource

import com.example.footballstore.data.model.Category
import javax.inject.Inject

class FakeCategoryDataSource @Inject constructor() {

    private val categories = mutableListOf(
        Category(1, "Camisetas", "Camisetas oficiales y de entrenamiento para hinchas y jugadores."),
        Category(2, "Botines", "Botines para césped natural, sintético y futsal."),
        Category(3, "Balones", "Balones de competencia, entrenamiento y colección."),
        Category(4, "Arquero", "Guantes, rodilleras y equipamiento especializado para porteros."),
        Category(5, "Entrenamiento", "Conos, escaleras, chalecos y accesorios para mejorar el rendimiento."),
        Category(6, "Accesorios", "Mochilas, termos, muñequeras y complementos útiles para el día a día."),
        Category(7, "Ropa deportiva", "Shorts, medias, casacas y prendas para entrenar con comodidad.")
    )

    fun getCategories(): List<Category> = categories.toList()

    fun getCategoryById(id: Int): Category? = categories.firstOrNull { it.id == id }

    fun addCategory(category: Category) {
        categories.add(category)
    }

    fun updateCategory(category: Category) {
        val index = categories.indexOfFirst { it.id == category.id }
        if (index >= 0) {
            categories[index] = category
        }
    }

    fun deleteCategory(id: Int) {
        categories.removeAll { it.id == id }
    }
}
