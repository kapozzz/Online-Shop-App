package com.kapozzz.profile.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kapozzz.common.navigation.api.FeatureApi
import com.kapozzz.common.navigation.api.Features
import com.kapozzz.profile.presentation.liked.LikedRoute
import com.kapozzz.profile.presentation.liked.LikedViewModel
import com.kapozzz.profile.presentation.profile.ProfileRoute
import com.kapozzz.profile.presentation.profile.ProfileViewModel

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

                val profileViewModel: ProfileViewModel = hiltViewModel()

                ProfileRoute(
                    state = profileViewModel.currentState,
                    effect = profileViewModel.effect,
                    setEvent = profileViewModel::setEvent
                )
            }
            composable(Features.Profile.Liked.SCREEN_ROUTE) {

                val likedViewModel: LikedViewModel = hiltViewModel()

                LikedRoute(
                    state = likedViewModel.currentState,
                    effect = likedViewModel.effect,
                    setEvent = likedViewModel::setEvent
                )
            }
        }
    }
}