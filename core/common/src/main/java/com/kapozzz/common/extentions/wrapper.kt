package com.kapozzz.common.extentions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.kapozzz.common.LocalNavigator
import com.kapozzz.common.navigation.Navigator

@Composable
fun NavGraphBuilder.wrap(
    navController: NavController,
    content: @Composable () -> Unit
) {

    val navigator = Navigator(navController)

    CompositionLocalProvider(
        LocalNavigator provides navigator,
        content = content
    )
}
