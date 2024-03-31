package com.kapozzz.common

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.staticCompositionLocalOf
import com.kapozzz.common.navigation.Navigator

val LocalNavigator = staticCompositionLocalOf<Navigator> {
    error("navigator not found")
}

val LocalSnackbarHost = staticCompositionLocalOf<SnackbarHostState> {
    error("snackbar host not found")
}