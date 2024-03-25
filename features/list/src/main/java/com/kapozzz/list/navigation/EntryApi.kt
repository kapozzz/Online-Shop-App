package com.kapozzz.list.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.kapozzz.common.navigation.api.FeatureApi

interface ListApi: FeatureApi

class ListApiImpl: ListApi {
    override fun registerGraph(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        InternalListFeatureApi.registerGraph(
            navController,
            navGraphBuilder
        )
    }
}