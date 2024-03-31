package com.example.shop_app

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.shop_app.navigation.AppNavGraph
import com.example.shop_app.navigation.NavigationProvider
import com.kapozzz.common.LocalNavigator
import com.kapozzz.common.LocalSnackbarHost
import com.kapozzz.common.navigation.BottomBarScreen
import com.kapozzz.common.navigation.Navigator
import com.kapozzz.common.navigation.api.Features
import com.kapozzz.ui.AppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RootScreen(
    isFirstStart: Boolean,
    navigationProvider: NavigationProvider,
) {

    val screens = listOf(
        BottomBarScreen.Cart,
        BottomBarScreen.List,
        BottomBarScreen.Profile
    )

    val startDestination =
        if (isFirstStart) Features.Entry.NESTED_ROUTE else Features.List.NESTED_ROUTE

    val navController = rememberNavController()

    val navigator = Navigator(navController)
    val snackbarHostState = remember { SnackbarHostState() }

    CompositionLocalProvider(
        LocalNavigator provides navigator,
        LocalSnackbarHost provides snackbarHostState
    ) {
        Scaffold(
            bottomBar = {
                BottomNavigation {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    screens.forEach { screen ->
                        val selected =
                            currentDestination?.hierarchy?.any { it.route == screen.route } == true
                        BottomNavigationItem(
                            modifier = Modifier
                                .background(AppTheme.colors.container),
                            selected = selected,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    modifier = Modifier
                                        .size(if (selected) 40.dp else 26.dp),
                                    imageVector = screen.icon,
                                    contentDescription = null,
                                    tint = if (selected) AppTheme.colors.primary else
                                        AppTheme.colors.onContainer
                                )
                            }
                        )
                    }
                }
            }, snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            }
        ) {
            AppNavGraph(
                navController = navController,
                navigationProvider = navigationProvider,
                startDestination = startDestination
            )
        }
    }
}