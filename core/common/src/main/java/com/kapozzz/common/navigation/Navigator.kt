package com.kapozzz.common.navigation

import androidx.navigation.NavController
import com.kapozzz.common.navigation.api.Features

class Navigator(
    private val navController: NavController
) {

    fun navigateToSignIn() {
        navController.navigate(Features.Entry.SCREEN_ROUTE)
    }

    fun navigateToMainScreen() {
        navController.navigate(Features.List.SCREEN_ROUTE) {
            popUpTo(Features.Entry.SCREEN_ROUTE) {
                inclusive = true
            }
        }
    }

    fun navigateToItemDetailScreen(itemID: String) {
        navController.navigate("${Features.Details.SCREEN_ROUTE_WITHOUT_ARGS}/$itemID")
    }

    fun back() {
        navController.popBackStack()
    }

    fun navigateToLikedScreen() {
        navController.navigate(Features.Profile.Liked.SCREEN_ROUTE)
    }

}