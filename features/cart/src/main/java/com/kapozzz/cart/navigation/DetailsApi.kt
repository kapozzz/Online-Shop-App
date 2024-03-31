package com.kapozzz.cart.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.kapozzz.common.navigation.api.FeatureApi

interface CartApi: FeatureApi

class CartApiImpl: CartApi {
    override fun registerGraph(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        InternalCartFeatureApi.registerGraph(
            navController,
            navGraphBuilder
        )
    }
}