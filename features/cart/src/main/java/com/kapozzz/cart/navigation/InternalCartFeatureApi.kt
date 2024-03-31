package com.kapozzz.cart.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kapozzz.cart.presentation.CartRoute
import com.kapozzz.common.navigation.api.FeatureApi
import com.kapozzz.common.navigation.api.Features

internal object InternalCartFeatureApi : FeatureApi {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation(
            startDestination = Features.Cart.SCREEN_ROUTE,
            route = Features.Cart.NESTED_ROUTE
        ) {
            composable(route = Features.Cart.SCREEN_ROUTE) {
                CartRoute()
            }
        }
    }
}