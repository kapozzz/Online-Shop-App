package com.kapozzz.list.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kapozzz.common.extentions.wrap
import com.kapozzz.common.navigation.api.FeatureApi
import com.kapozzz.common.navigation.api.Features
import com.kapozzz.list.presentation.MainScreenRoute
import com.kapozzz.list.presentation.MainScreenViewModel

internal object InternalListFeatureApi : FeatureApi {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation(
            startDestination = Features.List.SCREEN_ROUTE,
            route = Features.List.NESTED_ROUTE
        ) {
            composable(Features.List.SCREEN_ROUTE) {
                wrap(navController = navController) {
                    val mainScreenViewModel: MainScreenViewModel = hiltViewModel()
                    MainScreenRoute(
                        state = mainScreenViewModel.currentState,
                        effects = mainScreenViewModel.effect,
                        setEvent = mainScreenViewModel::setEvent
                    )
                }
            }
        }
    }
}