package com.example.footballstore.ui.screens.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.footballstore.ui.components.CartItemCard
import com.example.footballstore.ui.components.EmptyState
import com.example.footballstore.viewmodel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    productViewModel: ProductViewModel,
    snackbarHostState: SnackbarHostState,
    innerPadding: PaddingValues
) {
    val cartItems by productViewModel.cartItems.collectAsStateWithLifecycle()
    val message by productViewModel.message.collectAsStateWithLifecycle()

    LaunchedEffect(message) {
        if (!message.isNullOrBlank()) {
            snackbarHostState.showSnackbar(message!!)
            productViewModel.clearMessage()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        TopAppBar(
            title = {
                Column {
                    Text("Carrito", fontWeight = FontWeight.Bold)
                    Text(
                        "${productViewModel.getCartItemsCount()} articulos listos para comprar",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        )

        if (cartItems.isEmpty()) {
            EmptyState(
                title = "Tu carrito esta vacio",
                message = "Agrega productos desde el catalogo para continuar con tu compra.",
                modifier = Modifier.fillMaxSize()
            )
            return
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(top = 16.dp, bottom = 120.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(cartItems, key = { it.product.id }) { cartItem ->
                CartItemCard(
                    cartItem = cartItem,
                    categoryName = productViewModel.getCategoryName(cartItem.product.categoryId),
                    onIncrease = { productViewModel.increaseCartItem(cartItem.product.id) },
                    onDecrease = { productViewModel.decreaseCartItem(cartItem.product.id) },
                    onRemove = { productViewModel.removeFromCart(cartItem.product.id) }
                )
            }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(22.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Column(
                        modifier = Modifier.padding(18.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "Total: S/ %.2f".format(productViewModel.getCartTotal()),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Button(
                            onClick = { productViewModel.checkout() },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Finalizar compra")
                        }
                    }
                }
            }
        }
    }
}
