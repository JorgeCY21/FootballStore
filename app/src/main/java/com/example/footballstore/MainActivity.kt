package com.example.footballstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.footballstore.data.datasource.FakeCategoryDataSource
import com.example.footballstore.data.datasource.FakeProductDataSource
import com.example.footballstore.data.repository.CategoryRepositoryImpl
import com.example.footballstore.data.repository.ProductRepositoryImpl
import com.example.footballstore.ui.navigation.AppNavGraph
import com.example.footballstore.ui.theme.FootballStoreTheme
import com.example.footballstore.viewmodel.AppViewModelFactory
import com.example.footballstore.viewmodel.CategoryViewModel
import com.example.footballstore.viewmodel.ProductViewModel

class MainActivity : ComponentActivity() {

    private val categoryDataSource by lazy { FakeCategoryDataSource() }
    private val productDataSource by lazy { FakeProductDataSource() }

    private val categoryRepository by lazy {
        CategoryRepositoryImpl(categoryDataSource)
    }
    private val productRepository by lazy {
        ProductRepositoryImpl(productDataSource)
    }

    private val appViewModelFactory by lazy {
        AppViewModelFactory(
            productRepository = productRepository,
            categoryRepository = categoryRepository
        )
    }

    private val productViewModel: ProductViewModel by viewModels {
        appViewModelFactory
    }
    private val categoryViewModel: CategoryViewModel by viewModels {
        appViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FootballStoreTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavGraph(
                        productViewModel = productViewModel,
                        categoryViewModel = categoryViewModel,
                        innerPadding = innerPadding
                    )
                }
            }
        }
    }
}
