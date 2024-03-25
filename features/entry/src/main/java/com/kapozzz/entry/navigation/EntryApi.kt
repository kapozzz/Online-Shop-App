package com.kapozzz.entry.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.kapozzz.common.navigation.api.FeatureApi

interface EntryApi: FeatureApi

class EntryApiImpl: EntryApi {
    override fun registerGraph(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        InternalEntryFeatureApi.registerGraph(
            navController,
            navGraphBuilder
        )
    }
}