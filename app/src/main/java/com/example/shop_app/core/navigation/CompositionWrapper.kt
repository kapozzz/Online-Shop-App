package com.example.shop_app.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController

val LocalNavigator = staticCompositionLocalOf<Navigator> {
    error("navigator not found")
}

class CompositionWrapper(
    private val navController: NavController,
    private val navigator: Navigator = Navigator(navController)
) {
    @Composable
    fun Wrap(
        content: @Composable () -> Unit
    ) {
        CompositionLocalProvider(
            LocalNavigator provides navigator,
            content = content
        )
    }
}