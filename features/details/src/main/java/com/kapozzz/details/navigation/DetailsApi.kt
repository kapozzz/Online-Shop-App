package com.kapozzz.details.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.kapozzz.common.navigation.api.FeatureApi

interface DetailsApi: FeatureApi

class DetailsApiImpl: DetailsApi {
    override fun registerGraph(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        InternalDetailsFeatureApi.registerGraph(
            navController,
            navGraphBuilder
        )
    }
}