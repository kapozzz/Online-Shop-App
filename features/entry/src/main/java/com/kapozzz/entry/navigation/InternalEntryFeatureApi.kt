package com.kapozzz.entry.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kapozzz.common.extentions.wrap
import com.kapozzz.common.navigation.api.FeatureApi
import com.kapozzz.common.navigation.api.Features
import com.kapozzz.entry.presentation.SignInRoute
import com.kapozzz.entry.presentation.SignInViewModel

internal object InternalEntryFeatureApi : FeatureApi {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation(
            startDestination = Features.Entry.SCREEN_ROUTE,
            route = Features.Entry.NESTED_ROUTE
        ) {
            composable(Features.Entry.SCREEN_ROUTE) {
                wrap(navController = navController) {
                    val signInViewModel: SignInViewModel = hiltViewModel()
                    SignInRoute(
                        state = signInViewModel.currentState,
                        effects = signInViewModel.effect,
                        setEvent = signInViewModel::setEvent
                    )
                }
            }
        }
    }
}