package com.kapozzz.profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kapozzz.common.navigation.api.FeatureApi
import com.kapozzz.common.navigation.api.Features
import com.kapozzz.profile.presentation.ProfileRoute

internal object InternalProfileFeatureApi : FeatureApi {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation(
            startDestination = Features.Profile.SCREEN_ROUTE,
            route = Features.Profile.NESTED_ROUTE
        ) {
            composable(Features.Profile.SCREEN_ROUTE) {
                ProfileRoute()
            }
        }
    }
}