package com.kapozzz.profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.kapozzz.common.navigation.api.FeatureApi

interface ProfileApi: FeatureApi

class ProfileApiImpl: ProfileApi {
    override fun registerGraph(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        InternalProfileFeatureApi.registerGraph(
            navController,
            navGraphBuilder
        )
    }
}