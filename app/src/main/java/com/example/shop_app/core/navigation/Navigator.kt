package com.example.shop_app.core.navigation

import androidx.navigation.NavController

class Navigator(
    private val navController: NavController
) {

    fun navigateToSignIn() {
        navController.navigate(Routes.SIGN_IN_SCREEN)
    }

    fun navigateToMainScreen() {
        navController.navigate(Routes.MAIN_SCREEN)
    }

    fun back() {
        navController.popBackStack()
    }

}