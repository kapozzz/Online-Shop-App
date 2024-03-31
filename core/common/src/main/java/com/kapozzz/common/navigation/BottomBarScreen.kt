package com.kapozzz.common.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.kapozzz.common.R
import com.kapozzz.common.navigation.api.Features

sealed class BottomBarScreen(
    val route: String,
    val icon: ImageVector,
    @StringRes val resourceId: Int
) {
    data object List : BottomBarScreen(
        route = Features.List.NESTED_ROUTE,
        icon = Icons.Default.Menu,
        resourceId = R.string.products
    )

    data object Cart : BottomBarScreen(
        route = Features.Cart.NESTED_ROUTE,
        icon = Icons.Default.ShoppingCart,
        resourceId = R.string.cart
    )

    data object Profile : BottomBarScreen(
        route = Features.Profile.NESTED_ROUTE,
        icon = Icons.Default.AccountBox,
        resourceId = R.string.profile
    )

}