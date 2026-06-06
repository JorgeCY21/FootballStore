package com.example.footballstore.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.footballstore.ui.screens.cart.CartScreen
import com.example.footballstore.ui.screens.category.CategoryShopScreen
import com.example.footballstore.ui.screens.product.ProductDetailScreen
import com.example.footballstore.ui.screens.product.ProductListScreen
import com.example.footballstore.viewmodel.CategoryViewModel
import com.example.footballstore.viewmodel.ProductViewModel

private data class BottomNavItem(
    val route: String,
    val label: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

@Composable
fun AppNavGraph(
    productViewModel: ProductViewModel,
    categoryViewModel: CategoryViewModel,
    innerPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomItems = listOf(
        BottomNavItem(Routes.PRODUCT_LIST, "Inicio", Icons.Default.Home),
        BottomNavItem(Routes.CATEGORY_SHOP, "Categorias", Icons.Default.Storefront),
        BottomNavItem(Routes.CART, "Carrito", Icons.Default.ShoppingCart)
    )

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        bottomBar = {
            BottomAppBar {
                bottomItems.forEach { item ->
                    val isSelected = currentDestination?.hierarchy?.any { it.route == item.route } == true
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) },
                        colors = NavigationBarItemDefaults.colors()
                    )
                }
            }
        }
    ) { scaffoldPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.PRODUCT_LIST,
            modifier = Modifier
        ) {
            composable(Routes.PRODUCT_LIST) {
                ProductListScreen(
                    productViewModel = productViewModel,
                    onNavigateToDetail = { navController.navigate(Routes.productDetail(it)) },
                    snackbarHostState = snackbarHostState,
                    innerPadding = mergePadding(innerPadding, scaffoldPadding)
                )
            }
            composable(Routes.CATEGORY_SHOP) {
                CategoryShopScreen(
                    categoryViewModel = categoryViewModel,
                    productViewModel = productViewModel,
                    innerPadding = mergePadding(innerPadding, scaffoldPadding)
                )
            }
            composable(Routes.CART) {
                CartScreen(
                    productViewModel = productViewModel,
                    snackbarHostState = snackbarHostState,
                    innerPadding = mergePadding(innerPadding, scaffoldPadding)
                )
            }
            composable(
                route = Routes.PRODUCT_DETAIL,
                arguments = listOf(navArgument("productId") { type = NavType.IntType })
            ) { backStackEntry ->
                ProductDetailScreen(
                    productId = backStackEntry.arguments?.getInt("productId") ?: 0,
                    productViewModel = productViewModel,
                    onBack = { navController.popBackStack() },
                    onGoToCart = {
                        navController.navigate(Routes.CART) {
                            launchSingleTop = true
                        }
                    },
                    snackbarHostState = snackbarHostState,
                    innerPadding = mergePadding(innerPadding, scaffoldPadding)
                )
            }
        }
    }
}

private fun mergePadding(
    outerPadding: PaddingValues,
    innerPadding: PaddingValues
): PaddingValues {
    return PaddingValues(
        start = 0.dp,
        top = outerPadding.calculateTopPadding() + innerPadding.calculateTopPadding(),
        end = 0.dp,
        bottom = outerPadding.calculateBottomPadding() + innerPadding.calculateBottomPadding()
    )
}
