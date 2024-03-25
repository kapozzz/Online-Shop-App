package com.kapozzz.details.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.kapozzz.common.extentions.wrap
import com.kapozzz.common.navigation.api.FeatureApi
import com.kapozzz.common.navigation.api.Features
import com.kapozzz.details.presentation.ItemDetailsRoute
import com.kapozzz.details.presentation.ItemDetailsViewModel

internal object InternalDetailsFeatureApi : FeatureApi {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation(
            startDestination = Features.Details.SCREEN_ROUTE,
            route = Features.Details.NESTED_ROUTE
        ) {
            composable(
                route = Features.Details.SCREEN_ROUTE,
                arguments = listOf(navArgument("itemID") { type = NavType.StringType })
            ) {
                wrap(navController = navController) {
                    val itemID = it.arguments?.getString("itemID")
                    val itemDetailsViewModel: ItemDetailsViewModel = hiltViewModel()
                    itemID?.let {
                        itemDetailsViewModel.setItemID(itemID)
                    } ?: throw IllegalStateException("item id is null")
                    ItemDetailsRoute(
                        state = itemDetailsViewModel.currentState,
                        setEvent = itemDetailsViewModel::setEvent,
                        effects = itemDetailsViewModel.effect
                    )
                }
            }
        }
    }
}