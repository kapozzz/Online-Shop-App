package com.example.shop_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun AppNavGraph(
    navController: NavHostController,
    navigationProvider: NavigationProvider,
    startDestination: String
) {

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        navigationProvider.entry.registerGraph(
            navController, this
        )

        navigationProvider.list.registerGraph(
            navController, this
        )

        navigationProvider.details.registerGraph(
            navController, this
        )

    }

}