package com.example.footballstore.ui.navigation

object Routes {
    const val PRODUCT_LIST = "product_list"
    const val PRODUCT_DETAIL = "product_detail/{productId}"
    const val CATEGORY_SHOP = "category_shop"
    const val CART = "cart"
    const val PRODUCT_CREATE = "product_create"
    const val PRODUCT_EDIT = "product_edit/{productId}"
    const val CATEGORY_LIST = "category_list"
    const val CATEGORY_CREATE = "category_create"
    const val CATEGORY_EDIT = "category_edit/{categoryId}"

    fun productDetail(productId: Int) = "product_detail/$productId"
    fun productEdit(productId: Int) = "product_edit/$productId"
    fun categoryEdit(categoryId: Int) = "category_edit/$categoryId"
}
